package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nowdz.ui.ArticleEnregistreActivity
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper

class FavorisFragment : Fragment(),GlobalHelper {
    private var linktext : TextView?=null
    private var favoris1 : CardView?= null
    private var favoris2 : CardView?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_favoris,null)
        linktext = v.findViewById(R.id.linktext)
        favoris1 = v.findViewById(R.id.favoris1)
        favoris2 = v.findViewById(R.id.favoris2)
        val nombreArticle = ArticleController.tailleFavoris()

        val titre = favoris1!!.findViewById<TextView>(R.id.cad2_news_titre)
        println(titre.text)
        if (nombreArticle<=2){
            linktext!!.visibility = View.GONE
            when(nombreArticle){
                0->{
                    hideElement(favoris1!!)
                    hideElement(favoris2!!)
                }
                1->{
                    hideElement(favoris1!!)
                }
            }
        }else{
            linktext = v.findViewById(R.id.linktext)
        }





        linktext!!.setOnClickListener{
            switchActivity(v.context, ArticleEnregistreActivity::class.java,activity!!)
        }

        return v
    }


    /**
     * cacher favoris1
     */
    fun hideElement(v : View){
        v.visibility = View.GONE
    }


}
