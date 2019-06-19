package com.example.nowdz.ui.Adapter

import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.ViewGroup


import android.view.LayoutInflater

import android.view.View
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.ui.Adapter.NewAdapter
import com.islem.rvhlibrary.RecycleViewHelper


class TitlePagerAdapter(var activity : FragmentActivity?) : PagerAdapter(),RecycleViewHelper {
    override var itemRecycleView: RecyclerView? = null
    private var listArticle = ArrayList<Article>()
    private var newsRecyclerView: RecyclerView? = null
    private var newsAdapter: NewAdapter? = null
    private var numItems: ArrayList<Int> = ArrayList()
    private var titleItem : ArrayList<String> = ArrayList()

    /**
     * instancier l'item de titre
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.inter_fragment_title, container, false)
        initRvNews(view,getColorItem(position))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return numItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): String? {
        return titleItem[position]
    }

    fun getColorItem(position: Int): Int {
        return numItems[position]
    }

    fun addAllNum(items: List<Int>) {
        numItems = ArrayList(items)
    }

    fun addAllTitle(item : List<String>){
        titleItem = ArrayList(item)
    }
    private fun initRvNews(v: View,category: Int){
        println(category)
        listArticle = ArticleController.ListPerCategory(category)
        println(listArticle.toString())
        newsAdapter = NewAdapter(listArticle,v.context,v,activity)
        initLineaire(v,R.id.title_content_rv,LinearLayoutManager.VERTICAL,newsAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }



}