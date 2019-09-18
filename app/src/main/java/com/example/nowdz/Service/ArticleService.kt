package com.example.nowdz.Service

import com.example.nowdz.model.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.math.BigInteger

interface ArticleService {

    @GET("articles")
    fun getArticles(@Query("page") page: Int) : Call<ResponseArticles>

    @GET("journals")
    fun getJournals() : Call<ResponseSite>

    @POST("user/article/add")
    fun addFavoris(@Query("id") id : String,@Body article : Article) : Call<Any>

    @POST("user/article/remove")
    fun removeFavoris(@Query("id")id : String,@Body requestFavoris : RequestFavoris): Call<Any>

    @POST("theme/add")
    fun addTheme(@Query("id") id : String,@Body theme : String): Call<Any>

    @POST("theme/remove")
    fun removeTheme(@Query("id")id : String,@Body theme : String): Call<Any>
    @POST("site/add")

    fun addSite(@Query("id") id : String,@Body site : String): Call<Any>

    @POST("site/remove")
    fun removeSite(@Query("id")id : String,@Body site : String): Call<Any>

    @GET("user/articles")
    fun getAllFavoris(@Query("id")id : String) : Call<ArrayList<Article>>

}