package com.example.hopelastrestart1.ui.home.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.ProjectFragmentAdapter
import com.example.hopelastrestart1.databinding.ActivityOrganizationBinding
import com.example.hopelastrestart1.databinding.ActivityProjectBinding
import com.example.hopelastrestart1.view.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener


class ProjectActivity : BaseActivity() {
    lateinit var activityProjectBinding: ActivityProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        activityProjectBinding = ActivityProjectBinding.inflate(layoutInflater)
        contentFrameLayout.addView(activityProjectBinding!!.root)
        //drawerLayout.addView(contentView, 0)
        title = "Projects"
        activityProjectBinding.tabLayout.addTab(
            activityProjectBinding.tabLayout.newTab().setText("Projects")
        )
        activityProjectBinding.tabLayout.addTab(
            activityProjectBinding.tabLayout.newTab().setText("Users")
        )
        activityProjectBinding.tabLayout.addTab(
            activityProjectBinding.tabLayout.newTab().setText("Stores")
        )
        activityProjectBinding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        activityProjectBinding.tabLayout.setTabTextColors(
            getResources().getColor(R.color.clrLightBlue),
            getResources().getColor(R.color.clrMaroon)
        );

        val madapter = ProjectFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            activityProjectBinding.tabLayout.tabCount
        )
        activityProjectBinding.viewPager!!.adapter = madapter
        activityProjectBinding.viewPager.addOnPageChangeListener(
            TabLayoutOnPageChangeListener(
                activityProjectBinding.tabLayout
            )
        )
        activityProjectBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                activityProjectBinding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}