package com.example.hopelastrestart1.ui.siteEngineer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.TaskFragmentAdapter
import com.example.hopelastrestart1.adapter.fragmentAdapters.SiteEngFragmentAdapter
import com.example.hopelastrestart1.databinding.ActivitySiteEngBinding
import com.example.hopelastrestart1.databinding.ActivityTaskBinding
import com.example.hopelastrestart1.view.BaseActivity
import com.google.android.material.tabs.TabLayout

class SiteEngActivity : BaseActivity() {
    lateinit var tabLayout_site: TabLayout
    lateinit var viewPager_site: ViewPager
    lateinit var binding: ActivitySiteEngBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)


        title = "Tasks"
        tabLayout_site = findViewById<TabLayout>(R.id.tab_site)
        viewPager_site = findViewById(R.id.viewPager_site)
        tabLayout_site.addTab(tabLayout_site.newTab().setText("Received Tasks"))
        tabLayout_site.addTab(tabLayout_site.newTab().setText("Submitted Reports"))
        //  tabLayout_task.addTab(tabLayout_task.newTab().setText("AssignBy Task"))
        //tabLayout_task.addTab(tabLayout_task.newTab().setText("Dprs Received"))

        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        tabLayout_site.tabGravity = TabLayout.GRAVITY_FILL
        val madapter = SiteEngFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            tabLayout_site.tabCount
        )
        viewPager_site!!.adapter = madapter
        viewPager_site.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabLayout_site
            )
        )
        tabLayout_site.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_site.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


}