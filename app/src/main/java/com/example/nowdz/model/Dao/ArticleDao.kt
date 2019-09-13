package com.example.nowdz.model.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowdz.model.Article


@Dao
interface ArticleDao {
    @Insert
    fun insert(article: Article)

    @Query("DELETE FROM article_table")
    fun deleteAllArticles()

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article_table LIMIT 2")
    fun getTwoFavoris(): LiveData<List<Article>>

    @Query("DELETE FROM article_table WHERE id=:id")
    fun deleteArticle(id: Int)

    @Query("SELECT * FROM article_table LIMIT (SELECT COUNT(*) FROM article_table) OFFSET 2")
    fun getRestFavoris() : LiveData<List<Article>>





}