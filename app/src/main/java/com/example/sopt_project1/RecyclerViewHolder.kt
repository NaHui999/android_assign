package com.example.sopt_project1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){//val,var하면 선언과 동시에 초기화.
private val title:TextView=itemView.findViewById(R.id.item_title) //바인딩시킴.
    private val subTitle:TextView=itemView.findViewById(R.id.item_subtitle)

    fun onBind(data: RecyclerData){//sampledata.kt가 객체로 들어오게됨.
        title.text=data.title
        subTitle.text=data.subTitle
    }
}