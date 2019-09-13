package com.example.nowdz.ui.Adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup


import android.view.LayoutInflater

import android.view.View
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.ui.Adapter.NewAdapter


class TitlePagerAdapter(var activity : FragmentActivity?) : PagerAdapter(), RecycleViewHelper {
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView? = null
    private var listArticle = ArrayList<Article>()
    private var newsRecyclerView: androidx.recyclerview.widget.RecyclerView? = null
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
        initLineaire(v,R.id.title_content_rv,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,newsAdapter as androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>)
    }



}