package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){//val,var하면 선언과 동시에 초기화.
private val title:TextView=itemView.findViewById(R.id.item_title) //바인딩시킴.
    private val subTitle:TextView=itemView.findViewById(R.id.item_subtitle)

    val cardview3:CardView = itemView.findViewById(R.id.cardView3)

    fun onBind(data: RecyclerData) {//sampledata.kt가 객체로 들어오게됨.
        title.text = data.title
        subTitle.text = data.subTitle

        cardview3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val context: Context = v!!.context
                val detailIntent = Intent(v!!.context, Activity_Recycler_Detail::class.java)
                detailIntent.putExtra("title", data.title)
                detailIntent.putExtra("subtitle", data.subTitle)
                detailIntent.putExtra("date", data.date)
                detailIntent.putExtra("add", data.add)
                context.startActivity(detailIntent)
            }
        })

    }

}