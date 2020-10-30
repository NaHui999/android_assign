package com.example.sopt_project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity__recycler__detail.*

class Activity_Recycler_Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__recycler__detail)

        val title=intent.getStringExtra("title")
        title_ch.text=title


        val subtitle=intent.getStringExtra("subtitle")
        subtitle_ch.text=subtitle

        val date=intent.getStringExtra("date")
        date_ch.text=date

        val add=intent.getStringExtra("add")
        add_ch.text=add

    }
}