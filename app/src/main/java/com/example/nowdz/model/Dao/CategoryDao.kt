package com.example.nowdz.model.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import com.example.nowdz.model.Categories

@Dao
interface CategoryDao {

    @Insert

    fun insert(category: Categories)

    @Query("SELECT * FROM category")
    fun getAllCategories() : LiveData<List<Categories>>

    @Query("SELECT * FROM category WHERE categoryId = :id")
    fun getCategorie(id : Int) : LiveData<Categories>

    @Query("UPDATE category SET affiche = :affiche where categoryId = :category ")
    fun updateAffich(affiche : Int,category : Int)

    @Query("UPDATE category SET suivi = :suivi where categoryId = :category ")
    fun updateSuivi(suivi : Int,category : Int)

    @Query("SELECT * FROM category WHERE affiche=1")
    fun getCategoryAffiche() : LiveData<List<Categories>>

    @Query("SELECT * FROM category WHERE suivi=1")
    fun getCategorySuivi() : LiveData<List<Categories>>
}