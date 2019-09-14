package com.example.nowdz.Service

import com.example.nowdz.model.Article
import com.example.nowdz.model.ResponseArticles
import com.example.nowdz.model.Source
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("articles")
    fun getArticles(@Query("page") page: Int) : Call<ResponseArticles>

    @GET("journals")
    fun getJournals() : Call<List<Source>>
}