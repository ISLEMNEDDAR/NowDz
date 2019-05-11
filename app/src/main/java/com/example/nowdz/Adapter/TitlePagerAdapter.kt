package com.example.nowdz.Adapter

import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.ViewGroup


import android.view.LayoutInflater

import android.view.View
import com.example.nowdz.R
import com.example.nowdz.helper.onWebView


class TitlePagerAdapter(var activity : FragmentActivity?) : PagerAdapter() {
    private val list = ArrayList<String>()
    private var newsRecyclerView: RecyclerView? = null
    private var newsAdapter: NewAdapter? = null
    private var mItems: List<String> = ArrayList()
    private var web : onWebView?=null
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.inter_fragment_title, container, false)
        initRvNews(view)
        ajouterNews()
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return mItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): String? {
        return mItems[position]
    }

    fun getColorItem(position: Int): String {
        return mItems[position]
    }

    fun addAll(items: List<String>) {
        mItems = ArrayList(items)
    }
    private fun initRvNews(v: View){
        newsRecyclerView = v.findViewById(R.id.title_content_rv)
        newsAdapter = NewAdapter(list,v.context,v,activity)
        val horizontalLayoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView!!.layoutManager = horizontalLayoutManager
        newsRecyclerView!!.adapter = newsAdapter
    }
    private fun ajouterNews(){
        list.clear()
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        newsAdapter!!.notifyDataSetChanged()
    }


}