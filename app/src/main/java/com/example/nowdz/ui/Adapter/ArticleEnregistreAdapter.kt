package com.example.nowdz.Adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.nowdz.*
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.onWebView

class ArticleEnregistreAdapter constructor(
    private val newsList: ArrayList<String>,
    internal var context: Context,
    var view: View,
    var activity: Activity?

)
    : RecyclerView.Adapter<ArticleEnregistreAdapter.ArticleViewHolder>(),onWebView,GlobalHelper {
    //constructor(newsList: ArrayList<String>,context: Context,view: View) : this(newsList,context,view,null)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_article_enregistre, parent, false)

        return ArticleViewHolder(newView)
    }

    override fun onBindViewHolder(holder:ArticleViewHolder, position: Int) {
        holder.popup.setOnClickListener {
            val popupMenu = PopupFct(this.context, it,activity!!)
            popupMenu.onCLick()
            popupMenu.inflate(R.menu.menu_popup)
        }

//        holder.hyperlink.setOnClickListener {onlink!!.showSaved()}

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var popup : ImageView = view.findViewById(R.id.card1_menu)
        internal var access : ImageView = view.findViewById(R.id.card1_newspaper)

    }

}
