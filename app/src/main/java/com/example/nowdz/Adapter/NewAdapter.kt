package com.example.nowdz.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.nowdz.ArticleActivity
import com.example.nowdz.BaseActivity
import com.example.nowdz.R
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.onWebView

class NewAdapter constructor(
    private val newsList: ArrayList<String>,
    internal var context: Context,
    var view: View,
    var activity: FragmentActivity?

)
    : RecyclerView.Adapter<NewAdapter.NewsViewHolder>(),onWebView,GlobalHelper {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_news_secondaire, parent, false)
        return NewsViewHolder(newView)
    }

    override fun onBindViewHolder(holder:NewsViewHolder, position: Int) {

  //      var onlink : NewAdapter.onLink? = null
        val classement = position+2
        holder.posiotion.text = "$classement."
        holder.popup.setOnClickListener {
            val popupMenu = PopupFct(context, it,activity!!)
            popupMenu.onCLick()
            popupMenu.inflate(R.menu.menu_popup)
            try {
                val fieldMPopup = PopupFct::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception){
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }

//        holder.hyperlink.setOnClickListener {onlink!!.showSaved()}

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var posiotion : TextView = view.findViewById(R.id.clasement_news)
        internal var popup : ImageView = view.findViewById(R.id.card2_menu)
//        internal var hyperlink : TextView = view.findViewById(R.id.linktext)
//        internal var webview : WebView = view.findViewById(R.id.web_view)


    }

}
