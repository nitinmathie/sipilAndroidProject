package com.example.hopelastrestart1.adapter.fragmentAdapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.*
import com.example.hopelastrestart1.ui.siteEngineer.tabs.ReceivedTasksFragment
import com.example.hopelastrestart1.ui.siteEngineer.tabs.SubmittedReportsFragment


class SiteEngFragmentAdapter(
    plenActivity: TaskActivity,
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
                    ReceivedTasksFragment()
                }
                1 -> {
                    SubmittedReportsFragment()
                }

                /*  3 -> {
                      AssignTaskFragment()
                  }
                  4 -> {
                      ReportTaskFragment()
                  }*/

                else -> {
                    TaskFragment()
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