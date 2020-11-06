package com.example.sopt_project1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regist_fin_btn.setOnClickListener {



           // val text1 = regi_name_input.text.toString()
           // val text2 = regi_id_input.text.toString()
           // val text3 = regi_pw_input.text.toString()

            if(regi_name_input.text.length==0 || regi_id_input.text.length==0 || regi_pw_input.text.length==0) {
                //빈칸이 있다는 토스트메세지
                    Toast.makeText(this, "채워지지 않은 칸이 존재합니다.", Toast.LENGTH_LONG).show()

            }
            else if(regi_name_input.text.length>0 && regi_id_input.text.length>0 && regi_pw_input.text.length>0){
                //회원가입완료 toastmessage
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_LONG).show()

                val intent2 = Intent(this, MainActivity::class.java)
                //startActivity(intent2)

                intent2.putExtra("id",regi_id_input.text.toString())
                intent2.putExtra("pw",regi_pw_input.text.toString())
                //setResult(Activity.RESULT_OK,intent2)
                startActivityForResult(intent2,200)
                finish()

            }


        }

    }
}