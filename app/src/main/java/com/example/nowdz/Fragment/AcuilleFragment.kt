package com.example.nowdz.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.nowdz.Adapter.NewAdapter
import com.example.nowdz.ArticleActivity
import com.example.nowdz.R
import com.example.nowdz.helper.PopupFct
import com.example.nowdz.helper.onWebView


class AcuilleFragment : Fragment(),onWebView {
    private val newsList = ArrayList<String>()
    private var newsRecyclerView: RecyclerView? = null
    private var newsAdapter: NewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_accuille,null)
        initRvNews(v)
        ajouterNews()
        (v.findViewById<ImageView>(R.id.card1_menu)).setOnClickListener {
            val popupMenu = PopupFct(this.context!!, it, activity!!)
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
        /***/
        return v

    }
    private fun initRvNews(v: View){
        newsRecyclerView = v.findViewById(R.id.recycle_news_acuille)
        newsAdapter = NewAdapter(newsList,v.context,v,activity)
        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView!!.layoutManager = horizontalLayoutManager
        newsRecyclerView!!.adapter = newsAdapter
    }
    private fun ajouterNews(){
        newsList.add("a")
        newsList.add("a")
        newsList.add("a")
        newsList.add("a")
        newsAdapter!!.notifyDataSetChanged()
    }


}

