package com.example.nowdz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.nowdz.Adapter.ArticleEnregistreAdapter
import com.example.nowdz.Adapter.NewAdapter
import com.example.nowdz.helper.RecycleViewHelper
const val currentView =R.layout.activity_main2
class Main2Activity : BaseActivity(),RecycleViewHelper {
    override var itemRecycleView: RecyclerView? = null
    private var articleList = ArrayList<String>()
    private var articleAdapter : ArticleEnregistreAdapter? = null

    private var backarrow : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(currentView)
        backarrow = findViewById(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().getBackStackEntryCount() > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()

        }
        val view = findViewById<View>(android.R.id.content)
        init(view)
    }
    private fun init(v : View){
        articleAdapter = ArticleEnregistreAdapter(articleList,v.context,v)
        initLineaire(v,R.id.recycleview_enregistre,LinearLayoutManager.VERTICAL,articleAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        ajouter()
    }

    private fun ajouter(){
        for (i in 1..4){
            articleList.add(i.toString())
        }
        articleAdapter!!.notifyDataSetChanged()
    }



}
