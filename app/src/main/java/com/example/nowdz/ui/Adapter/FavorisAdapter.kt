package com.example.nowdz.ui.Adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.ui.activities.AffichageActivity

class FavorisAdapter constructor(
    private var newsList: ArrayList<Article>,
    internal var context: Context,
    var view: View,
    var activity: Activity?

) : RecyclerView.Adapter<FavorisAdapter.ArticleViewHolder>(), onWebView, GlobalHelper {
    //constructor(newsList: ArrayList<String>,context: Context,view: View) : this(newsList,context,view,null)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_news_favorite, parent, false)

        return ArticleViewHolder(newView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = newsList[position]
        val suivi = holder.favoris
        toggleSuivi(article.suivi,suivi,R.drawable.ic_saved,R.drawable.ic_save)
        holder.card.setOnClickListener {
            switchActivityExtra(this.context, AffichageActivity::class.java,activity!!,"article",article)
        }
        holder.popup.setOnClickListener {
            val popupMenu = PopupFct(context, it,activity!!)
            popupMenu.onCLick()
            popupMenu.inflat(R.menu.menu_popup)
        }
        suivi.setOnClickListener {
            suiviProc(suivi,article)
            newsList = ArticleController.avoirDeuxFavoris()
            notifyDataSetChanged()
        }
        ArticleController.construireArticle(article,holder.imageNews,holder.logo,holder.titre,holder.date)


    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var card : CardView = view.findViewById(R.id.card_favorite)
        internal var logo : ImageView = view.findViewById(R.id.site_logo)
        internal var titre : TextView = view.findViewById(R.id.cad2_news_titre)
        internal var imageNews : ImageView = view.findViewById(R.id.image_card2)
        internal var date : TextView = view.findViewById(R.id.cadr1_date)
        internal var popup : ImageView = view.findViewById(R.id.card2_menu)
        internal var favoris : ImageView = view.findViewById(R.id.favoris_card)
    }

}
