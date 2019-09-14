package com.example.nowdz.ui.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.R
import com.example.nowdz.model.Article
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_affichage.*

class AffichageActivity : BaseActivity() {
    /**
     * Declaration
     */
    var image_news :ImageView? = null
    var titre : TextView? = null
    var date : TextView? = null
    var contenu : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage)
        setSupportActionBar(toolbar_affichage)
        /**
         * Initialisation
         */
        image_news = findViewById(R.id.affichage_image_news)
        titre = findViewById(R.id.affichage_titre_news)
        date = findViewById(R.id.affichage_date)
        contenu  = findViewById(R.id.affichage_content)

        initAffichage()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        back_toolbar!!.setOnClickListener{
            if ( getFragmentManager().getBackStackEntryCount() > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()
        }

        share_toolbar!!.setOnClickListener{
            val shareintent = Intent(Intent.ACTION_SEND)
            shareintent.type="type/palin"
            val sharebody ="The body"
            val sharesub= "The subject"
            shareintent.putExtra(Intent.EXTRA_SUBJECT,sharebody)
            shareintent.putExtra(Intent.EXTRA_TEXT,sharesub)
            ContextCompat.startActivity(
                this@AffichageActivity,
                Intent.createChooser(shareintent, "Share article"),
                Bundle()
            )
        }

    }

    private fun initAffichage(){
        val intent = intent
        if (intent != null){
            val article = intent.getParcelableExtra<Article>("article")
            if(article != null){
                Picasso.get().load(article.urltoImage).into(image_news)
                titre!!.text = article.titre
                date!!.text = article.published_at.toString()
                contenu!!.text = article.content
            }
        }
    }

}
