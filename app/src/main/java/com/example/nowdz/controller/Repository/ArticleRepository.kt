package com.example.nowdz.controller.Repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.nowdz.model.Article
import com.example.nowdz.model.Dao.ArticleDao
import com.example.nowdz.model.db.ArticleDatebase

class ArticleRepository(application: Application) {

    private var articleDao : ArticleDao

    private var allArticle : LiveData<List<Article>>


    init {
        val database : ArticleDatebase? = ArticleDatebase.getInstance(application.applicationContext)
        articleDao = database!!.articleDao()

        // avoir tous les articles
        allArticle = articleDao.getAllArticles()
    }

    fun insert(article : Article){
        val insertArticleAsyncTask = InsertArticleAsyncTask(articleDao)
            .execute(article)
    }



    fun deleteAllArticle(){
        val deleteAllArticlesAsyncTask = DeleteAllArticlesAsyncTask(
            articleDao
        ).execute()
    }

    fun getAllArticle() : LiveData<List<Article>>{
        return allArticle
    }

    fun getTwoArticle() : LiveData<List<Article>>{
        return articleDao.getTwoFavoris()
    }

    fun deleteArticle(id : Int){
        DeleteArticleAsyncTask(articleDao).execute(id)
    }
    fun getRestFavoris() : LiveData<List<Article>>{
        return articleDao.getRestFavoris()
    }
    private class InsertArticleAsyncTask(articleDao : ArticleDao) : AsyncTask<Article, Unit, Unit>(){
        val articleDao = articleDao
        override fun doInBackground(vararg p0: Article?) {
            articleDao.insert(p0[0]!!)
        }

    }
    private class DeleteAllArticlesAsyncTask(val articleDao: ArticleDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            articleDao.deleteAllArticles()
        }
    }
    private class DeleteArticleAsyncTask(articleDao : ArticleDao) : AsyncTask<Int, Unit, Unit>(){
        val articleDao = articleDao
        override fun doInBackground(vararg p0: Int?) {
            articleDao.deleteArticle(p0[0]!!)
        }

    }
}