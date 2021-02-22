package com.example.hopelastrestart1.adapter.fragmentAdapters

import SeDashboardFragment
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectsFragment
import com.example.hopelastrestart1.ui.home.project.tabs.StoresFragment
import com.example.hopelastrestart1.ui.home.project.tabs.UsersFragment
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.ui.siteEngineer.tabs.SeActivitiesFragment
import com.example.hopelastrestart1.ui.siteEngineer.tabs.SubmittedDprsFragment

class SeHomeFragmentAdapter(
    siteHomeActivity: SiteHomeActivity,
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
                    SeDashboardFragment()
                }
                1 -> {
                    SeActivitiesFragment()
                }
                2 -> {
                    SubmittedDprsFragment()
                }
                else ->{
                    SeDashboardFragment()

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
