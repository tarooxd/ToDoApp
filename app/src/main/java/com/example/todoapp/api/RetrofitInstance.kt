package com.example.testeendpoints.api

import com.example.todoapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofrit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiService by lazy{
        retrofrit.create(ApiService::class.java)
    }
}