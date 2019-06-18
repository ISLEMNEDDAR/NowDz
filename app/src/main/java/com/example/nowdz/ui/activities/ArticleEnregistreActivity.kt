package com.example.nowdz.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.nowdz.Adapter.ArticleEnregistreAdapter
import com.example.nowdz.R
import com.example.nowdz.ui.activities.BaseActivity
import com.islem.rvhlibrary.RecycleViewHelper

const val currentView = R.layout.activity_article_enregistre
class ArticleEnregistreActivity : BaseActivity(), RecycleViewHelper {
    override var itemRecycleView: RecyclerView? = null
    private var articleList = ArrayList<String>()
    private var articleAdapter : ArticleEnregistreAdapter? = null

    private var backarrow : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(currentView)
        backarrow = findViewById(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().backStackEntryCount > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()

        }
        val view = findViewById<View>(android.R.id.content)
        init(view)
    }
    private fun init(v : View){
        articleAdapter = ArticleEnregistreAdapter(articleList,v.context,v,this@ArticleEnregistreActivity)
        initLineaire(v, R.id.recycleview_enregistre,LinearLayoutManager.VERTICAL,articleAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        ajouter()
    }

    private fun ajouter(){
        for (i in 1..4){
            articleList.add(i.toString())
        }
        articleAdapter!!.notifyDataSetChanged()
    }



}
