package com.example.sopt_project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_first.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var ReqresAdapter: ReqresAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ReqresAdapter = ReqresAdapter(view.context)

        reqres_rcv.adapter = ReqresAdapter
        reqres_rcv.layoutManager = LinearLayoutManager(view.context) //이 레이아웃을 격자형으로든 바꿀 수 있다.

        //채워야함. 데이터 받아와야함.
        val call: Call<ReqresData> = ReqresServiceImpl.service.GetReqres()
        call.enqueue(object : Callback<ReqresData> {
            override fun onFailure(call: Call<ReqresData>, t: Throwable) {
                //통신 실패 로직
                //Toast.makeText(this@FirstFragment, "로그인 실패", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<ReqresData>,
                    response: Response<ReqresData>
            ) {
                response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            ReqresAdapter.data=it.data as MutableList<ReqresData.DataX>
                            ReqresAdapter.notifyDataSetChanged()
                        } ?: showError(response.errorBody())
            }

            private fun showError(error: ResponseBody?) {
                val e = error ?: return
                val ob = JSONObject(e.string())
                //Toast.makeText(this@MainActivity, ob.getString("message"), Toast.LENGTH_LONG).show()
            }
        })

        ReqresAdapter.notifyDataSetChanged()

        super.onViewCreated(view, savedInstanceState)
    }


}