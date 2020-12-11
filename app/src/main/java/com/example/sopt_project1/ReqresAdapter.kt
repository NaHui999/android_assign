package com.example.sopt_project1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReqresAdapter (private val context: Context) : RecyclerView.Adapter<ReqresViewHolder>(){
    var data= mutableListOf<ReqresData.DataX>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqresViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.reqres_list,parent,false)
        return ReqresViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size //ppt에서 :Int =data.size랑 같은 의미. 줄일수 있음.
    }

    override fun onBindViewHolder(holder: ReqresViewHolder, position: Int) {
        holder.onBind(data[position])
    }


}