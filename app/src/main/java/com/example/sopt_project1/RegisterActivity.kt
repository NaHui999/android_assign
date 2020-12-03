package com.example.sopt_project1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regist_fin_btn.setOnClickListener {

            val name=regi_name_input.text.toString()
            val id=regi_id_input.text.toString()
            val pw=regi_pw_input.text .toString()

            //회원가입 창 채워지는 것을 알려주는 메세지
            if(regi_name_input.text.length==0 || regi_id_input.text.length==0 || regi_pw_input.text.length==0) {
                //빈칸이 있다는 토스트메세지
                    Toast.makeText(this, "채워지지 않은 칸이 존재합니다.", Toast.LENGTH_LONG).show()
            }
            else if(regi_name_input.text.length>0 && regi_id_input.text.length>0 && regi_pw_input.text.length>0){
                //회원가입완료 toastmessage
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_LONG).show()

                val regi_intent = Intent(this, MainActivity::class.java)
                //startActivity(intent2)

                regi_intent.putExtra("id",regi_id_input.text.toString())
                regi_intent.putExtra("pw",regi_pw_input.text.toString())

                /*서버에 회원가입 정보 전달하는 코드*/
                val call: Call<ResponseRegiData> = SoptRegiServiceImpl.regiService.postRegi(
                        RequestRegiData(email = id, password = pw, userName = name)
                )
                call.enqueue(object : Callback<ResponseRegiData> {
                    override fun onFailure(call: Call<ResponseRegiData>, t: Throwable) {
                        //통신 실패 로직
                        Toast.makeText(this@RegisterActivity, "회원가입 실패", Toast.LENGTH_LONG).show()
                        Log.d("register", "regi_error")
                    }

                    override fun onResponse(
                            call: Call<ResponseRegiData>,
                            response: Response<ResponseRegiData>
                    ) {

                        //Log.d("login","ok")
                        response.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let { it ->
                                    it.data.let { data ->
                                        Toast.makeText(this@RegisterActivity, "${data.userName} 님 환영합니다.",Toast.LENGTH_LONG).show()
                                    }
                                } ?: showError(response.errorBody())
                    }

                    private fun showError(error: ResponseBody?) {
                        val e = error ?: return
                        val ob = JSONObject(e.string())
                        Toast.makeText(this@RegisterActivity, ob.getString("message"), Toast.LENGTH_LONG)
                                .show()
                    }
                })

                //Intent를 통해 Register Activity->Main Activity
                //setResult(Activity.RESULT_OK,intent2)
                startActivityForResult(regi_intent,200)
                finish()

            }

        }

    }
}