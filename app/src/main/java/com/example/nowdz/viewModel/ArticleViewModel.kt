package com.example.nowdz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nowdz.controller.Repository.ArticleRepository
import com.example.nowdz.model.Article

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    // repository
    private var repository : ArticleRepository =
        ArticleRepository(application)

    // allArticles
    private var allArticles : LiveData<List<Article>> =
        repository.getAllArticle()

    fun insert(note : Article){
        repository.insert(note)
    }

    fun deleteAllArticles(){
        repository.deleteAllArticle()
    }

    fun getAllArticle() : LiveData<List<Article>>{
        return allArticles
    }

    fun getTwoArticle() : LiveData<List<Article>>{
        return repository.getTwoArticle()
    }
    fun deleteArticle(id : Int){
        repository.deleteArticle(id)
    }
    fun getRestArticle() : LiveData<List<Article>>{
        return repository.getRestFavoris()
    }
}