package com.example.hopelastrestart1.ui.planEngineer.Task

import android.os.Bundle
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.TaskFragmentAdapter
import com.example.hopelastrestart1.databinding.ActivityTaskBinding
import com.example.hopelastrestart1.databinding.ActivityUpdateProfileBinding
import com.example.hopelastrestart1.view.BaseActivity
import com.google.android.material.tabs.TabLayout

class TaskActivity : BaseActivity() {
    lateinit var tabLayout_task: TabLayout
    lateinit var viewPager_task: ViewPager
    lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        // setContentView(R.layout.activity_task)
        title = "Tasks"
        tabLayout_task = findViewById<TabLayout>(R.id.TabLayout_Task)
        viewPager_task = findViewById(R.id.viewPager_Task)
        tabLayout_task.addTab(tabLayout_task.newTab().setText("Tasks"))
        tabLayout_task.addTab(tabLayout_task.newTab().setText("Assigned Tasks"))
        tabLayout_task.addTab(tabLayout_task.newTab().setText("Received Task Reports"))
        //  tabLayout_task.addTab(tabLayout_task.newTab().setText("AssignBy Task"))
        //tabLayout_task.addTab(tabLayout_task.newTab().setText("Dprs Received"))

        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        tabLayout_task.tabGravity = TabLayout.GRAVITY_FILL
        val madapter = TaskFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            tabLayout_task.tabCount
        )
        viewPager_task!!.adapter = madapter
        viewPager_task.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabLayout_task
            )
        )
        tabLayout_task.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_task.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}