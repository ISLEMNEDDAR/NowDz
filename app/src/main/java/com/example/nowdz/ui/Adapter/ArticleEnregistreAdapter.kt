package com.example.nowdz.ui.Adapter

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.nowdz.*
import com.example.nowdz.Service.ArticleService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.model.RequestFavoris
import com.example.nowdz.ui.activities.AffichageActivity
import com.example.nowdz.viewModel.ArticleViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleEnregistreAdapter constructor(
    internal var context: Context,
    var view: View,
    var activity: Activity?

)
    : androidx.recyclerview.widget.RecyclerView.Adapter<ArticleEnregistreAdapter.ArticleViewHolder>()
    ,onWebView,GlobalHelper,SharedPreferenceInterface {
    //constructor(newsList: ArrayList<String>,context: Context,view: View) : this(newsList,context,view,null)

    private var newsList : List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_article_enregistre, parent, false)

        return ArticleViewHolder(newView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = newsList[position]
        val suivi = holder.favoris
        toggleSuivi(article.suivi!!,suivi,R.drawable.ic_saved,R.drawable.ic_save)
        holder.card.setOnClickListener {
            switchActivityExtra(this.context, AffichageActivity::class.java,activity!!,"article",article)
        }
        var articleService = ServiceBuilder.buildService(ArticleService::class.java)
        holder.popup.setOnClickListener {
            val popupMenu = PopupFct(context, it,activity!!)
            popupMenu.onCLick()
            popupMenu.inflat(R.menu.menu_popup)
        }
        suivi.setOnClickListener {
            removeFavoris(articleService,article,holder,suivi)
        }
        ArticleController.construireArticle(article,holder.imageNews,holder.logo,holder.titre,holder.date)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setArticles(articles: List<Article>) {
        this.newsList = articles
        notifyDataSetChanged()
    }
    inner class ArticleViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        internal var card : androidx.cardview.widget.CardView = view.findViewById(R.id.card_enregistrer)
        internal var logo : ImageView = view.findViewById(R.id.logo)
        internal var titre : TextView = view.findViewById(R.id.titre)
        internal var imageNews : ImageView = view.findViewById(R.id.news_image)
        internal var date : TextView = view.findViewById(R.id.cadr1_date)
        internal var popup : ImageView = view.findViewById(R.id.card1_menu)
        internal var favoris : ImageView = view.findViewById(R.id.favoris_card)

    }


    fun removeFavoris(articleService : ArticleService, article : Article, holder : ArticleEnregistreAdapter.ArticleViewHolder,suivi : ImageView){
        var request = articleService.removeFavoris(avoirIdUser(context).toString(),
            RequestFavoris(article.journal!!.name!!,article.titre!!)
        )
        request.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    article.suivi = false
                    suiviProc(suivi,article)
                    var articleViewModel = ViewModelProviders.of(context as FragmentActivity).get(
                        ArticleViewModel::class.java)
                    articleViewModel.deleteArticle(article.id!!)
                    notifyDataSetChanged()
                    ArticleController.construireArticle(article,holder.imageNews,holder.logo,holder.titre,holder.date)
                }else{
                    removeFavoris(articleService,article,holder,suivi)
                }

            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                removeFavoris(articleService,article,holder,suivi)
            }
        })
    }

}
