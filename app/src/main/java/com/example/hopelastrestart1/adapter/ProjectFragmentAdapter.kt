package com.example.hopelastrestart1.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectsFragment
import com.example.hopelastrestart1.ui.home.project.tabs.StoresFragment
import com.example.hopelastrestart1.ui.home.project.tabs.UsersFragment

class ProjectFragmentAdapter(
    projectActivity: ProjectActivity,
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
                    ProjectsFragment()
                }
                1 -> {
                    UsersFragment()
                }
                2 -> {
                    StoresFragment()
                }
                else ->{
                    UsersFragment()

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