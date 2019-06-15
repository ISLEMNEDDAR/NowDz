package com.example.nowdz

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_affichage.*

class AffichageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage)
        setSupportActionBar(toolbar_affichage)

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

}
