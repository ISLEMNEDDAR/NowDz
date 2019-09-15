package com.example.nowdz.controller

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.R
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.model.Source
import com.example.nowdz.viewModel.ArticleViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object ArticleController {
        private lateinit var articleViewModel: ArticleViewModel




            private var listArticle = ArrayList<Article>()
            private var listArticleFavoris = ArrayList<Article>()

   fun setArticle(articles : List<Article>){
        this.listArticle.clear()
        this.listArticle.addAll(articles)
   }
    fun addArticles(articles : List<Article>){
        this.listArticle.addAll(articles)
    }



    /**
     * construire l'article i
     */
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun construireArticle(articleUn: Article, image_news:ImageView
                          , logo:ImageView
                          , titre : TextView
                          , heurs : TextView
    ){
        val format = SimpleDateFormat("yyyy/MM/dd")
        val dateToday = articleUn.published_at
        val year = format.format(dateToday)
        Picasso.get().load(articleUn.urltoImage).into(image_news)
        Picasso.get().load(articleUn.journal!!.logo).into(logo)
        titre.text = articleUn.titre
        heurs.text = "a la une -$year"
    }


    /**
     * Avoir favoris
     */
    fun getAllFavoris() : ArrayList<Article>{
        val listfavoris = ArrayList<Article>()
        listfavoris.addAll(this.listArticleFavoris)
        return listfavoris
    }


    /**
     *
     */

    /**
     * list Article par categori
     */
    fun ListPerCategory(category : String) : ArrayList<Article>{
        val listCategory = ArrayList<Article>()
        for(article in listArticle){
            if (article.categorie.equals(category)){
                listCategory.add(article)
            }
        }
        return listCategory
    }

}