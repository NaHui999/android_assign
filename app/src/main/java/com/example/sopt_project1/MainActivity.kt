package com.example.sopt_project1

import android.R.attr.button
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.mock.Calls.response


class MainActivity : AppCompatActivity() {
    //lateinit var sharedPreferences: SharedPreferences
    lateinit var id: String
    lateinit var pw: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Edittext에 값 있으면 변화
        id_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) { }
        })

        /* 자동로그인 고치던 것
        val pref=getSharedPreferences("pref_login", Context.MODE_PRIVATE)
        val id=pref.getString("id", "")
        val pw=pref.getString("pw", "")
        if(id!=""&&pw!=""){
            id_input.setText(id.toString())
            pw_input.setText(pw.toString())
        }*/


        //로그인버튼
        login_btn.setOnClickListener{
            id=id_input.text.toString()
            pw=pw_input.text.toString()

            val call: Call<ResponseLoginData> = SoptServiceImpl.service.postLogin(
                RequestLoginData(email = id, password = pw)
            )
            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    //통신 실패 로직
                    Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            it.data.let { data ->
                                Toast.makeText(this@MainActivity,"${data.userName} 님 환영합니다.",Toast.LENGTH_LONG).show()
                            }

                        } ?: showError(response.errorBody())
                }

                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(this@MainActivity, ob.getString("message"), Toast.LENGTH_LONG)
                        .show()
                }
            })

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

            val intent_rec= Intent(this, ProfileActivity::class.java)
            //RecyclerActivity였던거 MainActivity2(ProfileActivity) 로 바꿈.
            startActivity(intent_rec)
            finish()
        }

        //회원가입창으로 넘어가기
        register_btn.setOnClickListener{
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

        //회원가입이후 login 창 왔을때 이미 입력되어 있도록.
        //override onActivityResult랑 연계되면 이거 없이도 되야할 것 같은데 왜 이러는지 확인해보기.***
        //setActivity와 startActivityforResult 차이 좀더 찾아보기.
        onActivityResult(200, RESULT_OK, intent)
        // -->함수를 불러야 했던것임. intent가 적용된게 1개라서 굳이 이름 안써도 되나
       // id_input.setText(intent.getStringExtra("id"))
       // pw_input.setText(intent.getStringExtra("pw"))



    }

    //id랑 pw 넘겨주려고 override 하는것.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            when(requestCode){
                200 -> {
                    id_input.setText(data!!.getStringExtra("id"))
                    //왜 이렇게 했을땐 setText 바로 안뜨는지.
                    pw_input.setText(data.getStringExtra("pw"))
                    //왜 여기 data뒤엔 !! 넣으면 워닝 뜨는지.
                }

            }

        }
    }



}