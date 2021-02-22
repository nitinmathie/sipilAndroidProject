package com.example.hopelastrestart1.ui.Inventory.inventoryManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.TaskFragmentAdapter
import com.example.hopelastrestart1.adapter.fragmentAdapters.InventoryFragmentAdapter
import com.google.android.material.tabs.TabLayout

class InventoryHomeActivity : AppCompatActivity() {
    lateinit var TabLayout_Inventory: TabLayout
    lateinit var viewPager_Inventory: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_home)
        title = "Site Engineer"
        TabLayout_Inventory = findViewById<TabLayout>(R.id.TabLayout_Inventory)
        viewPager_Inventory = findViewById(R.id.viewPager_Inventory)
        TabLayout_Inventory.addTab(TabLayout_Inventory.newTab().setText("DashBoard"))
        TabLayout_Inventory.addTab(TabLayout_Inventory.newTab().setText("My Activities"))
        TabLayout_Inventory.addTab(TabLayout_Inventory.newTab().setText("DPRs"))

        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        TabLayout_Inventory.tabGravity = TabLayout.GRAVITY_FILL
        val madapter = InventoryFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            TabLayout_Inventory.tabCount
        )
        viewPager_Inventory!!.adapter = madapter
        viewPager_Inventory.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(TabLayout_Inventory))
        TabLayout_Inventory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_Inventory.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}