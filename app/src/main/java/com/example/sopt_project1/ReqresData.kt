package com.example.sopt_project1

data class ReqresData(
    val data: List<DataX>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
){
    data class DataX(
            val avatar: String,
            val email: String,
            val first_name: String,
            val id: Int,
            val last_name: String
    )
}