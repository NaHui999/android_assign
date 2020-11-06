package com.example.sopt_project1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerFragment : Fragment() {
    private lateinit var RecyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)

        //val textView: TextView = view.findViewById(R.id.fragment_txt)
        //textView.text=name

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        RecyclerAdapter = RecyclerAdapter(view.context)

        main_rcv.adapter = RecyclerAdapter
        main_rcv.layoutManager = LinearLayoutManager(view.context) //이 레이아웃을 격자형으로든 바꿀 수 있다.

        RecyclerAdapter.data = mutableListOf(
            RecyclerData("이름","나희정","2020/10/1","이름에 대한 자세한 설명"),
            RecyclerData("나이","22","2020/10/2","나이에 대한 자세한 설명"),
            RecyclerData("파트","안드로이드","2020/10/3","파트에 대한 자세한 설명"),
            RecyclerData("GitHub","www.github.com/nahui999","2020/10/4","깃헙에 대한 자세한 설명"),
            RecyclerData("Blog","None","2020/10/5","블로그에 대한 자세한 설명"),
            RecyclerData("Sopt","www.sopt.org","2020/10/6","솝트에 대한 자세한 설명")
        )
        RecyclerAdapter.notifyDataSetChanged()

        super.onViewCreated(view, savedInstanceState)
    }
}