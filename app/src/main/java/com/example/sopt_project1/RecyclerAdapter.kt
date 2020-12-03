package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private val context:Context) : RecyclerView.Adapter<RecyclerViewHolder>(){
    var data= mutableListOf<RecyclerData>()

    var changeViewType=0//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view=
                when (viewType){
                    1->{
                        LayoutInflater.from(context).inflate(R.layout.item_list_grid,parent,false)
                    }

                    2->{
                        LayoutInflater.from(context).inflate(R.layout.item_list_recycler,parent,false)
                    }

                    else->{
                        LayoutInflater.from(context).inflate(R.layout.item_list_recycler,parent,false)
                    }

                }
        //val view=LayoutInflater.from(context).inflate(R.layout.item_list_recycler,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size //ppt에서 :Int =data.size랑 같은 의미. 줄일수 있음.
    }
    /**/
    override fun getItemViewType(position: Int): Int {
        return changeViewType
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(data[position])



    }


}