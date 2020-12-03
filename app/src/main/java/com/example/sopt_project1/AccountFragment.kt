package com.example.sopt_project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    private lateinit var viewPagerAdapter:ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        //val textView: TextView = view.findViewById(R.id.fragment_txt)
        //textView.text=name

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment()
        )

        sample_tab_viewpager.adapter=viewPagerAdapter

        sample_tab.setupWithViewPager(sample_tab_viewpager)
        sample_tab.apply{
            getTabAt(0)?.text="PROJECTS"
            getTabAt(1)?.text="OTHER"
        }
    }
}