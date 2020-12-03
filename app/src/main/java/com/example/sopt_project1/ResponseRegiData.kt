package com.example.sopt_project1

data class ResponseRegiData(
    val data: DataXX,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class DataXX(
            val email: String,
            val password: String,
            val userName: String
    )
}