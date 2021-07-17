package com.example.hopelastrestart1.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanFragment

class PlanFragmentAdapter(
    planActivity: PlanActivity,
    supportFragmentManager: FragmentManager,
    val tabCount: Int
) {
    @Suppress("DEPRECATION")
    internal class MyAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int
    ) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> {
                    PlanFragment()
                }
               /* 1 -> {
                    TestFragment()
                }*/
                else ->{
                    PlanFragment()
                }
            }
        }
        override fun getCount(): Int {
            return totalTabs
        }
        fun getItemPosition(): Int {
            return POSITION_NONE
        }
    }
}