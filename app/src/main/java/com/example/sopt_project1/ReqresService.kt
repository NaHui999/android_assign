package com.example.sopt_project1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReqresService {
    @Headers("Content-Type:application/json")
    @GET("api/users?page=2")
    fun GetReqres(
    ) : Call<ReqresData>
}