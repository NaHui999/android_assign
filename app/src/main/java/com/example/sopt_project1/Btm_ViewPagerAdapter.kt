package com.example.sopt_project1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class Btm_ViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var fragments= listOf<Fragment>()
    override fun getItem(position:Int): Fragment =when(position){
        0->AccountFragment()
        1->RecyclerFragment()
        2->EmptyFragment()
        else->throw IllegalStateException("Unexpected position &position")
    }

    override fun getCount():Int =3

}