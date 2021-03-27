package com.example.hopelastrestart1.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.*


class TaskFragmentAdapter(
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
                    TaskFragment()
                }
                1 -> {
                    AssignTaskFragment()
                }
                2 -> {
                    ReportTaskFragment()
                }
              /*  3 -> {
                    AssignTaskFragment()
                }
                4 -> {
                    ReportTaskFragment()
                }*/

                else ->{
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