package com.example.sopt_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_profile.*
import kotlin.properties.Delegates

class ProfileActivity : AppCompatActivity() {
    private lateinit var btmViewpageradapter: Btm_ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)//나중에 activity_profile 로 바꿔야함.

        //BTM ViewPager 넘기기
        btmViewpageradapter=Btm_ViewPagerAdapter(supportFragmentManager)
        btmViewpageradapter.fragments= listOf(
            AccountFragment(),
            RecyclerFragment(),
            EmptyFragment()
        )

        sample_bottom_viewpager.adapter=btmViewpageradapter


        //뷰페이저를 슬라이드 했을때 그에 대응되는 하단 탭 변경
        sample_bottom_viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int){
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset:Float,
                positionOffsetPixels:Int
            ){}

            override fun onPageSelected(position:Int){
                sample_bottom_navi.menu.getItem(position).isChecked=true
            }
        })

        //하단탭을 눌렀을때 뷰페이저 화면 변경
        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()

            when(it.itemId){
                R.id.menu_account->index=0 //첫 화면은 인덱스 기준 0번째
                R.id.menu_recy->index=1
                R.id.menu_msg->index=2
            }
            sample_bottom_viewpager.currentItem=index
            true //불린 반환
        }

    }

}