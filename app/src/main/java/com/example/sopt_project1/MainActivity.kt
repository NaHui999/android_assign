package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var id: String
    lateinit var pw: String
    var isChecked: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref=getSharedPreferences("pref_login",Context.MODE_PRIVATE)
        val id=pref.getString("id","")
        val pw=pref.getString("pw","")
        if(id!=""&&pw!=""){
            id_input.setText(id.toString())
            pw_input.setText(pw.toString())
        }

        //로그인버튼
        login_btn.setOnClickListener{
            /*
            val pref=getSharedPreferences("pref_login",Context.MODE_PRIVATE)
            val editor=pref.edit()

            //위에랑 같지만 상황이 다르니까 풀어써놓음.
            if(pref.getString("id","").toString().length!=0&&pref.getString("pw","").toString().length!=0){
                val id=id_input.text.toString()
                val pw= pw_input.text.toString()

                editor.putString("id",id)
                editor.putString("pw",pw)
                editor.apply()

                Toast.makeText(this,"자동로그인됨",Toast.LENGTH_LONG).show()
            }
            */
            val intent_rec= Intent(this,ProfileActivity::class.java)
            //RecyclerActivity였던거 MainActivity2(ProfileActivity) 로 바꿈.
            startActivity(intent_rec)
            finish()
        }

        //회원가입창으로 넘어가기
        register_btn.setOnClickListener{
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        }

        //회원가입이후 login 창 왔을때 이미 입력되어 있도록.
        //override onActivityResult랑 연계되면 이거 없이도 되야할 것 같은데 왜 이러는지 확인해보기.***
        //setActivity와 startActivityforResult 차이 좀더 찾아보기.
        onActivityResult(200,RESULT_OK,intent)
        // -->함수를 불러야 했던것임. intent가 적용된게 1개라서 굳이 이름 안써도 되나
       // id_input.setText(intent.getStringExtra("id"))
       // pw_input.setText(intent.getStringExtra("pw"))



    }

    //id랑 pw 넘겨주려고 override 하는것.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            when(requestCode){
                200->{
                    id_input.setText(data!!.getStringExtra("id"))
                    //왜 이렇게 했을땐 setText 바로 안뜨는지.
                    pw_input.setText(data.getStringExtra("pw"))
                    //왜 여기 data뒤엔 !! 넣으면 워닝 뜨는지.
                }

            }

        }
    }



}