package com.example.nowdz.ui.activities.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.ui.activities.AffichageActivity
import kotlin.collections.ArrayList

class AcuilleFragment : Fragment(),onWebView,GlobalHelper, RecycleViewHelper {
    val PAGE_SIZE = 10
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView?=null
    private val newsList = ArrayList<Article>()
    private var newsRecyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var newsAdapter: NewAdapter? = null
    private val isLastPage = false
    private val currentPage = 1
    private val isLoading = false
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
        toggleSuivi(ArticleController.getFirstArticle().suivi,suivi,R.drawable.ic_saved,R.drawable.ic_save)
        (v.findViewById<androidx.cardview.widget.CardView>(R.id.card1_accuille)).setOnClickListener {
            switchActivityExtra(this.context!!, AffichageActivity::class.java,activity!!,"article",ArticleController.getFirstArticle())
        }
        (v.findViewById<ImageView>(R.id.card1_menu)).setOnClickListener {
            val popupMenu = PopupFct(this.context!!, it, activity!!)
            popupMenu.onCLick()
            popupMenu.inflat(R.menu.menu_popup)
        }
        (suivi).setOnClickListener{
            suiviProc(suivi,ArticleController.getFirstArticle())
        }
        /***/
        return v

    }

    @SuppressLint("SetTextI18n")
    private fun initFirstNews(){
        val articleUn = ArticleController.getFirstArticle()
        ArticleController.construireArticle(articleUn,image_news!!,logo!!,titre!!,heurs!!)

    }
    private fun initRvNews(v: View){
        newsAdapter = NewAdapter(newsList,v.context,v,activity)
        var rv = v.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycle_news_acuille)
        val horizontalLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            v.context,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
        rv!!.layoutManager = horizontalLayoutManager
        rv.adapter = newsAdapter
        val layoutManager = horizontalLayoutManager

        /**
         * ajudter le recycler view
         */
        val recyclerViewOnScrollListener = object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE
                    ) {
                        loadMoreItems()
                    }
                }
            }
        }
        rv.addOnScrollListener(recyclerViewOnScrollListener)
    }
    private fun ajouterNews(restArticle: ArrayList<Article>) {
        newsList.addAll(restArticle)
        newsAdapter!!.notifyDataSetChanged()
    }


    private fun loadMoreItems(){
        //ajouterNews(ArticleController.getRestArticle())
    }



}

