package com.example.nowdz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nowdz.controller.Repository.CategoryRepository
import com.example.nowdz.model.Categories

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    // repository
    private var repository : CategoryRepository =
        CategoryRepository(application)

    // allArticles
    private var allCategories : LiveData<List<Categories>> =
        repository.getAllCategories()

    fun getAllCategory() : LiveData<List<Categories>>{
        return allCategories
    }
    fun insert(category : Categories){
        repository.insert(category)
    }

    fun getCategories(id: Int) : LiveData<Categories>{
        return repository.getCategories(id)
    }

    fun getCategoriesAffich() : LiveData<List<Categories>>{
        return repository.getCategoriesAffich()
    }

    fun getCategoriesSuivi() : LiveData<List<Categories>>{
        return repository.getCategoriesSuivi()
    }

    fun updateAffich(affiche : Int,category: Int){
        repository.updateAffich(affiche,category)
    }

    fun updateSuivi(suivi : Int,category: Int){
        repository.updateSuivi(suivi,category)
    }
}