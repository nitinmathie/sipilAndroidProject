package com.example.hopelastrestart1.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.admin.AdminDFRsFragment
import com.example.hopelastrestart1.ui.admin.AdminDPRsFragment
import com.example.hopelastrestart1.ui.admin.AdminPLFragment
import com.example.hopelastrestart1.ui.admin.AdminReportsFragment
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectsFragment
import com.example.hopelastrestart1.ui.home.project.tabs.StoresFragment
import com.example.hopelastrestart1.ui.home.project.tabs.UsersFragment

class AdminFragmentAdapter(
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
                    AdminReportsFragment()
                }
                1 -> {
                    AdminDPRsFragment()
                }
                2 -> {
                    AdminDFRsFragment()
                }
                else -> {
                    AdminPLFragment()

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