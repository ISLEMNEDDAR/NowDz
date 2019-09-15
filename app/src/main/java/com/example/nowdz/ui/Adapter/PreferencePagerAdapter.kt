package com.example.nowdz.ui.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.nowdz.R
import com.example.nowdz.Service.ArticleService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.model.ResponseArticles
import com.example.nowdz.model.ResponseSite
import com.example.nowdz.model.Source
import com.example.nowdz.viewModel.CategoryViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreferencePagerAdapter(var activity : FragmentActivity?) : PagerAdapter(), RecycleViewHelper {
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView? = null
    private var newsRecyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var listSource = ArrayList<Source>()
    var newsService = ServiceBuilder.buildService(ArticleService::class.java)
    private var themeAdapter: ThemeAdapter? = null
    private var siteAdapter : SiteAdapter? = null
    private var numItems: ArrayList<Int> = ArrayList()
    private var titleItem : ArrayList<String> = ArrayList()

    /**
     * instancier l'item de titre
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.inter_prefe, container, false)
        Log.i("instantiate item pref",getColorItem(position).toString())
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
    private fun initRvNews(v: View, type: Int){
        // if 0 theme 1 site
        if(type == 1){
            println("je suis theme ")
            themeAdapter = ThemeAdapter(v.context,v,activity)
            initLineaire(v,R.id.prefe_content_rv,
                LinearLayoutManager.VERTICAL,themeAdapter as RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>)
            var categoryViewModel = ViewModelProviders.of(activity!!).get(
                CategoryViewModel::class.java)
            categoryViewModel.getAllCategory().observe(
                activity!!,
                Observer {
                    themeAdapter!!.setCategory(it!!)
                }
            )
        }else {
            siteAdapter = SiteAdapter(v.context,v,activity)
            initLineaire(v,R.id.prefe_content_rv,LinearLayoutManager.VERTICAL,siteAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
            val newsRequest = newsService.getJournals()
            newsRequest.enqueue(object : Callback<ResponseSite> {
                override fun onResponse(call: Call<ResponseSite>, response: Response<ResponseSite>) {
                    if(response.isSuccessful) {
                        siteAdapter!!.setSource(response.body()!!.journals)
                    }else{

                    }
                }
                override fun onFailure(call: Call<ResponseSite>, t: Throwable) {
                    println(t.message)

                }
            })
        }

    }



}