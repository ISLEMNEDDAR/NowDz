package com.example.nowdz.Service

import com.example.nowdz.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("auth/automob")
    fun setToken(@Header("Authorization") authorization:String, @Body idAutomobilste: User) : Call<User>
}