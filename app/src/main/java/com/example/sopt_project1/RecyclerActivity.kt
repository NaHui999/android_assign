package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    private lateinit var RecyclerAdapter: RecyclerAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.change_grid->{
                RecyclerAdapter.changeViewType=1
                main_rcv.apply {
                    layoutManager=GridLayoutManager(this@RecyclerActivity,2)
                }
            }
            R.id.change_linear->{
                RecyclerAdapter.changeViewType=0
                main_rcv.apply{
                    layoutManager=LinearLayoutManager(this@RecyclerActivity)
                }
            }
            R.id.logout->{
                val intent_main=Intent(this,MainActivity::class.java)
                val pref=getSharedPreferences("pref_login", Context.MODE_PRIVATE)
                val editor=pref.edit()
                editor.remove("id")
                editor.remove("pw")
                editor.commit()
                startActivity(intent_main)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        RecyclerAdapter = RecyclerAdapter(this)

        main_rcv.adapter = RecyclerAdapter
        main_rcv.layoutManager = LinearLayoutManager(this) //이 레이아웃을 격자형으로든 바꿀 수 있다.

        RecyclerAdapter.data = mutableListOf(
                RecyclerData("이름","나희정","2020/10/1","이름에 대한 자세한 설명"),
                RecyclerData("나이","22","2020/10/2","나이에 대한 자세한 설명"),
                RecyclerData("파트","안드로이드","2020/10/3","파트에 대한 자세한 설명"),
                RecyclerData("GitHub","www.github.com/nahui999","2020/10/4","깃헙에 대한 자세한 설명"),
                RecyclerData("Blog","None","2020/10/5","블로그에 대한 자세한 설명"),
                RecyclerData("Sopt","www.sopt.org","2020/10/6","솝트에 대한 자세한 설명")
        )
        RecyclerAdapter.notifyDataSetChanged()

    }

}