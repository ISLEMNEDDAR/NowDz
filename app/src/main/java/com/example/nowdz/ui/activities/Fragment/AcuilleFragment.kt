package com.example.nowdz.ui.activities.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.ui.Adapter.NewAdapter
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.ui.activities.AffichageActivity
import kotlin.collections.ArrayList


class AcuilleFragment : Fragment(),onWebView,GlobalHelper {
    private val newsList = ArrayList<Article>()
    private var newsRecyclerView: RecyclerView? = null
    private var newsAdapter: NewAdapter? = null
    /**
     * le contenu de card
     */
    var image_news : ImageView? = null
    var logo : ImageView? = null
    var titre : TextView? = null
    var heurs : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_accuille,null)
        val suivi =v.findViewById<ImageView>(R.id.card1_newspaper)
        image_news = v.findViewById(R.id.news_image)
        logo=v.findViewById(R.id.logo_icon)
        titre =v.findViewById(R.id.titre_news)
        heurs = v.findViewById(R.id.cadr1_date)
        initFirstNews()
        initRvNews(v)
        ajouterNews(ArticleController.getRestArticle())
        toggleSuivi(suivi.tag == "suivi",suivi,R.drawable.ic_saved,R.drawable.ic_save)
        (v.findViewById<CardView>(R.id.card1_accuille)).setOnClickListener {
            switchActivityExtra(this.context!!, AffichageActivity::class.java,activity!!,"article",ArticleController.getFirstArticle())
        }
        (v.findViewById<ImageView>(R.id.card1_menu)).setOnClickListener {
            val popupMenu = PopupFct(this.context!!, it, activity!!)
            popupMenu.onCLick()
            popupMenu.inflat(R.menu.menu_popup)
        }
        (suivi).setOnClickListener{
            suiviProc(suivi)
        }
        /***/
        return v

    }

    @SuppressLint("SetTextI18n")
    private fun initFirstNews(){
        var articleUn = ArticleController.getFirstArticle()
        ArticleController.construireArticle(articleUn,image_news!!,logo!!,titre!!,heurs!!)

    }
    private fun initRvNews(v: View){
        newsRecyclerView = v.findViewById(R.id.recycle_news_acuille)
        newsAdapter = NewAdapter(newsList,v.context,v,activity)
        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView!!.layoutManager = horizontalLayoutManager
        newsRecyclerView!!.adapter = newsAdapter
    }
    private fun ajouterNews(restArticle: ArrayList<Article>) {
        newsList.addAll(restArticle)
        newsAdapter!!.notifyDataSetChanged()
    }


}
