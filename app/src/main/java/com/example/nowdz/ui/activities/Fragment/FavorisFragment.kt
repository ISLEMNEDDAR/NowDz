package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nowdz.ui.activities.ArticleEnregistreActivity
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.ui.Adapter.FavorisAdapter
import com.islem.rvhlibrary.RecycleViewHelper

class FavorisFragment : Fragment(),GlobalHelper, RecycleViewHelper {
    override var itemRecycleView: RecyclerView? =null
    private var linktext : TextView?=null
    private var favorisAdapeter : FavorisAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_favoris,null)
        linktext = v.findViewById(R.id.linktext)

        /**
         * Initialier recycleView
         */
        val nombreArticle = ArticleController.tailleFavoris()
        favorisAdapeter = FavorisAdapter(ArticleController.avoirDeuxFavoris(),this.context!!,v,activity)
        initLineaire(v,R.id.list_favoris,LinearLayoutManager.VERTICAL,favorisAdapeter as RecyclerView.Adapter<RecyclerView.ViewHolder>)

        /**
         *
         */
        if (nombreArticle<=2){
            linktext!!.visibility = View.GONE
        }else{
            linktext!!.visibility = View.VISIBLE

        }
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
