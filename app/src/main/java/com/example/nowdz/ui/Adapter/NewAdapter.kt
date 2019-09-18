package com.example.nowdz.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nowdz.R
import com.example.nowdz.Service.ArticleService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.*
import com.example.nowdz.model.Article
import com.example.nowdz.model.RequestFavoris
import com.example.nowdz.model.User
import com.example.nowdz.ui.activities.AffichageActivity
import com.example.nowdz.viewModel.ArticleViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewAdapter constructor(
    private var newsList: ArrayList<Article>,
    internal var context: Context,
    var view: View,
    var activity: FragmentActivity?
)
    : androidx.recyclerview.widget.RecyclerView.Adapter<NewAdapter.NewsViewHolder>()
    ,onWebView,GlobalHelper,SharedPreferenceInterface{



    private lateinit var articleViewModel: ArticleViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_news_secondaire, parent, false)
        articleViewModel = ViewModelProviders.of(activity!!).get(
            ArticleViewModel::class.java
        )
        return NewsViewHolder(newView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

  //      var onlink : NewAdapter.onLink? = null
        val article = newsList[position]
        val classement = position+2
        val suivi = holder.favoris
        var articleService = ServiceBuilder.buildService(ArticleService::class.java)
        articleViewModel.articleExist(article.titre!!,article.journal!!.name!!).observe(
            activity!!,
            Observer {

                article.suivi = it.size>0
                if(article.suivi!!) article.id = it[0].id
                toggleSuivi(article.suivi!!,suivi,R.drawable.ic_saved,R.drawable.ic_save)
                holder.posiotion.text = "$classement."
                suivi.setOnClickListener {
                    suiviProc(suivi,article)
                    if (article.suivi!!){
                        /**
                         * update article to unsuive
                         */
                            removeFavoris(articleService,article,holder)


                    }else{
                        /**
                         * suivre article
                         */
                       addFavoris(articleService,article,holder)

                    }

                }
            }
        )
        holder.card.setOnClickListener {
            switchActivityExtra(this.context, AffichageActivity::class.java,activity!!,"article",article)
        }
        holder.popup.setOnClickListener {
            val popupMenu = PopupFct(context, it,activity!!)
            popupMenu.onCLick()
            popupMenu.inflat(R.menu.menu_popup)
        }


    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNews(news : List<Article>){
        this.newsList.clear()
        this.newsList = news as ArrayList<Article>
        notifyDataSetChanged()
    }
    fun addNews(news : List<Article>){
        this.newsList.addAll(news)
        notifyDataSetChanged()
    }
    inner class NewsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        internal var card : androidx.cardview.widget.CardView = view.findViewById(R.id.card_secondaire)
        internal var posiotion : TextView = view.findViewById(R.id.clasement_news)
        internal var logo : ImageView = view.findViewById(R.id.site_logo)
        internal var titre : TextView = view.findViewById(R.id.cad2_news_titre)
        internal var imageNews : ImageView = view.findViewById(R.id.image_card2)
        internal var date : TextView = view.findViewById(R.id.cadr1_date)
        internal var popup : ImageView = view.findViewById(R.id.card2_menu)
        internal var favoris : ImageView = view.findViewById(R.id.favoris_card)
    }

    fun removeFavoris(articleService : ArticleService,article : Article,holder :NewsViewHolder){
        var request = articleService.removeFavoris(avoirIdUser(context).toString(),
            RequestFavoris(article.journal!!.name!!,article.titre!!)
        )
        request.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    article.suivi = false
                    println(article.id)
                    articleViewModel.deleteArticle(article.id!!)
                    ArticleController.construireArticle(article,holder.imageNews,holder.logo,holder.titre,holder.date)

                }else{
                    removeFavoris(articleService,article,holder)
                }

            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                removeFavoris(articleService,article,holder)
            }
        })
    }

    fun addFavoris(articleService : ArticleService,article : Article,holder :NewsViewHolder){
        var request = articleService.removeFavoris(avoirIdUser(context).toString(),
            RequestFavoris(article.journal!!.name!!,article.titre!!)
        )
        request.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    article.suivi = true
                    articleViewModel.insert(article)
                    ArticleController.construireArticle(article,holder.imageNews,holder.logo,holder.titre,holder.date)
                }else{
                    addFavoris(articleService,article,holder)
                }

            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                addFavoris(articleService,article,holder)
            }
        })
    }

}
