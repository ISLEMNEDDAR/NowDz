package com.example.nowdz.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.nowdz.*
import com.example.nowdz.Fragment.AcuilleFragment
import com.example.nowdz.helper.onWebView

class ArticleEnregistreAdapter constructor(
    private val newsList: ArrayList<String>,
    internal var context: Context,
    var view: View,
    var activity: BaseActivity?

)
    : RecyclerView.Adapter<ArticleEnregistreAdapter.ArticleViewHolder>(),onWebView {
    constructor(newsList: ArrayList<String>,context: Context,view: View) : this(newsList,context,view,null)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_article_enregistre, parent, false)

        return ArticleViewHolder(newView)
    }

    override fun onBindViewHolder(holder:ArticleViewHolder, position: Int) {
        holder.popup.setOnClickListener {
            val popupMenu = PopupMenu(context, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_popup_save -> {
                        Toast.makeText(context, "Showing Save Toast!", Toast.LENGTH_LONG).show()
                        item.setIcon(R.drawable.ic_saved)
                        true
                    }
                    R.id.menu_popup_share -> {
                        val shareintent = Intent(Intent.ACTION_SEND)
                        shareintent.type="type/palin"
                        val sharebody ="The body"
                        val sharesub= "The subject"
                        shareintent.putExtra(Intent.EXTRA_SUBJECT,sharebody)
                        shareintent.putExtra(Intent.EXTRA_TEXT,sharesub)
                        ContextCompat.startActivity(
                            context,
                            Intent.createChooser(shareintent, "Share article"),
                            Bundle()
                        )



                        Toast.makeText(context, "Showing Share Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.menu_popup_access -> {
                        Toast.makeText(context, "Showing access Toast!", Toast.LENGTH_SHORT).show()
                        Log.i("isClicked","log success")
                        showActivity(activity!!,ArticleActivity::class.java)
                        true
                        //var wv : WebView = view.findViewById(R.id.read_webview)
                    }
                    R.id.menu_popup_hide -> {
                        Toast.makeText(context, "Showing Hide Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }


            popupMenu.inflate(R.menu.menu_popup)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
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

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var popup : ImageView = view.findViewById(R.id.card1_menu)
        internal var access : ImageView = view.findViewById(R.id.card1_newspaper)

    }

}
