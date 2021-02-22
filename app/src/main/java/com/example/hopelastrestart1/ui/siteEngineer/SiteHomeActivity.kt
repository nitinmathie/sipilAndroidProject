package com.example.hopelastrestart1.ui.siteEngineer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.TaskFragmentAdapter
import com.example.hopelastrestart1.adapter.fragmentAdapters.SeHomeFragmentAdapter
import com.google.android.material.tabs.TabLayout

class SiteHomeActivity : AppCompatActivity() {
    lateinit var tabLayout_home_se: TabLayout
    lateinit var viewPager_home_se: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_home)
        title = "Site Engineer"
        tabLayout_home_se = findViewById<TabLayout>(R.id.TabLayout_home_se)
        viewPager_home_se = findViewById(R.id.viewPager_home_se)
        tabLayout_home_se.addTab(tabLayout_home_se.newTab().setText("DashBoard"))
        tabLayout_home_se.addTab(tabLayout_home_se.newTab().setText("My Activities"))
        tabLayout_home_se.addTab(tabLayout_home_se.newTab().setText("DPRs"))

        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        tabLayout_home_se.tabGravity = TabLayout.GRAVITY_FILL
        val madapter = SeHomeFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            tabLayout_home_se.tabCount
        )
        viewPager_home_se!!.adapter = madapter
        viewPager_home_se.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_home_se))
        tabLayout_home_se.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_home_se.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}