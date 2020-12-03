package com.example.sopt_project1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptRegiService {
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postRegi(
            @Body body : RequestRegiData
    ) : Call<ResponseRegiData>
}