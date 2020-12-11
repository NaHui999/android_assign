package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import android.view.PointerIcon.load
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.System.load
import java.util.ServiceLoader.load

class ReqresViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){//val,var하면 선언과 동시에 초기화.
    private val id: TextView =itemView.findViewById(R.id.id) //바인딩시킴.
    private val first_name: TextView =itemView.findViewById(R.id.first_name)
    private val last_name: TextView =itemView.findViewById(R.id.last_name)
    private val avatar: ImageView =itemView.findViewById(R.id.avatar)
    private val email: TextView =itemView.findViewById(R.id.email)



    fun onBind(data: ReqresData.DataX) {
        id.text=data.id.toString()
        first_name.text=data.first_name
        last_name.text=data.last_name
        email.text=data.email
        //아바타는 어떻게 해야하나?
        Glide.with(itemView)
                .load(data.avatar)
                .placeholder(R.drawable.earth)
                .error(R.drawable.earth)
                .into(avatar)

    }

}