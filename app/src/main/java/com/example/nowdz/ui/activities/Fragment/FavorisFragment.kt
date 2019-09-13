package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nowdz.ui.activities.ArticleEnregistreActivity
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.model.Article
import com.example.nowdz.ui.Adapter.FavorisAdapter
import com.example.nowdz.viewModel.ArticleViewModel

class FavorisFragment : Fragment(),GlobalHelper, RecycleViewHelper {
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView? =null
    private var linktext : TextView?=null
    private var favorisAdapeter : FavorisAdapter? = null
    private lateinit var articleViewModel: ArticleViewModel
    private var nombreArticle : Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_favoris,null)
        linktext = v.findViewById(R.id.linktext)

        /**
         * Initialier recycleView
         */

        favorisAdapeter = FavorisAdapter(this.context!!,v,activity)
        initLineaire(v,R.id.list_favoris,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,favorisAdapeter as androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>)

        /**
         * Avoir les deux premiere favoris
         */
        // viewModel
        articleViewModel = ViewModelProviders.of(this).get(
            ArticleViewModel::class.java
        )
        articleViewModel.getTwoArticle().observe(
            this,
            Observer<List<Article>> {
                favorisAdapeter!!.setArticles(it!!)
                nombreArticle =  favorisAdapeter!!.itemCount

                Log.i("nombreArticle : ", nombreArticle.toString())
                if (nombreArticle<2){
                    linktext!!.visibility = View.GONE
                }else{
                    linktext!!.visibility = View.VISIBLE

                }
            }
        )


        linktext!!.setOnClickListener{
            switchActivity(v.context, ArticleEnregistreActivity::class.java,activity!!)
        }
        return v
    }


    /**
     * cacher favoris1
     */
    fun hideElement(v : View){
        v.visibility = View.GONE
    }


}
