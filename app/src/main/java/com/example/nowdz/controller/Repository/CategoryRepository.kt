package com.example.nowdz.controller.Repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.model.Dao.ArticleDao
import com.example.nowdz.model.Dao.CategoryDao
import com.example.nowdz.model.db.ArticleDatebase
import java.util.*

class CategoryRepository(application: Application) {

    private lateinit var categoryDao : CategoryDao

    private lateinit var allCategory : LiveData<List<Categories>>


    init {
        val database : ArticleDatebase? = ArticleDatebase.getInstance(application.applicationContext)
        categoryDao = database!!.categoryDao()

        // avoir tous les articles
        allCategory = categoryDao.getAllCategories()
    }

    fun getAllCategories() : LiveData<List<Categories>> {
        return allCategory
    }

    fun getCategories(id: Int) : LiveData<Categories>{
        return categoryDao.getCategorie(id)
    }

    fun getCategoriesAffich() : LiveData<List<Categories>>{
        return categoryDao.getCategoryAffiche()
    }

    fun getCategoriesSuivi() : LiveData<List<Categories>>{
        return categoryDao.getCategorySuivi()
    }

    fun insert(cateegory : Categories){
        val insertArticleAsyncTask = CategoryRepository.insertCategoryAsyncTask(categoryDao)
            .execute(cateegory)
    }

    fun updateAffich(affiche : Int,category : Int){
        val insertArticleAsyncTask = CategoryRepository.updateAffichAsyncTask(categoryDao)
            .execute(affiche,category)
    }

    fun updateSuivi(suivi : Int,category: Int){
        val insertArticleAsyncTask = CategoryRepository.updateSuiviAsyncTask(categoryDao)
            .execute(suivi,category)
    }

    private class insertCategoryAsyncTask(categoryDao: CategoryDao) : AsyncTask<Categories, Unit, Unit>(){
        val categoryDao= categoryDao
        override fun doInBackground(vararg p0: Categories?) {
            categoryDao.insert(p0[0]!!)
        }

    }


    private class updateAffichAsyncTask(categoryDao: CategoryDao) : AsyncTask<Int, Unit, Unit>(){
        val categoryDao= categoryDao
        override fun doInBackground(vararg p0: Int?) {
            categoryDao.updateAffich(p0[0]!!,p0[1]!!)
        }

    }

    private class updateSuiviAsyncTask(categoryDao: CategoryDao) : AsyncTask<Int, Unit, Unit>(){
        val categoryDao= categoryDao
        override fun doInBackground(vararg p0: Int?) {
            categoryDao.updateSuivi(p0[0]!!,p0[1]!!)
        }

    }

}