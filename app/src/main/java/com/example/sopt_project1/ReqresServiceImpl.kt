package com.example.sopt_project1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReqresServiceImpl {
    private const val BASE_URL = "https://reqres.in/"
    private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val service : ReqresService = retrofit.create(ReqresService::class.java)
}