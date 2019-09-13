package com.example.nowdz.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nowdz.ui.Adapter.ArticleEnregistreAdapter
import com.example.nowdz.R
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.model.Article
import com.example.nowdz.viewModel.ArticleViewModel

const val currentView = R.layout.activity_article_enregistre
class ArticleEnregistreActivity : BaseActivity(), RecycleViewHelper {
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView? = null
    private var articleList = ArrayList<Article>()
    private var articleAdapter : ArticleEnregistreAdapter? = null
    private lateinit var articleViewModel: ArticleViewModel
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
        articleAdapter = ArticleEnregistreAdapter(v.context,v,this@ArticleEnregistreActivity)
        initLineaire(v, R.id.recycleview_enregistre,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,articleAdapter as androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>)
        articleViewModel = ViewModelProviders.of(this).get(
            ArticleViewModel::class.java
        )
        articleViewModel.getRestArticle().observe(
            this,
            Observer {
                articleAdapter!!.setArticles(it!!)
            }
        )

    }



}
