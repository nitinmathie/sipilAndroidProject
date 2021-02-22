package com.example.hopelastrestart1.ui.home.plen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.PlenFragmentAdapter
import com.google.android.material.tabs.TabLayout

class PlenActivity : AppCompatActivity() {
    lateinit var tabLayout_plen: TabLayout
    lateinit var viewPager_plen: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plen)
        title = "Plans"
        tabLayout_plen = findViewById<TabLayout>(R.id.TabLayout_Plen)
        viewPager_plen = findViewById(R.id.viewPager_Plen)
        tabLayout_plen.addTab(tabLayout_plen.newTab().setText("Plans"))
        tabLayout_plen.addTab(tabLayout_plen.newTab().setText("test"))
        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        tabLayout_plen.tabGravity = TabLayout.GRAVITY_FILL
        val madapter = PlenFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            tabLayout_plen.tabCount
        )
        viewPager_plen!!.adapter = madapter
        viewPager_plen.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_plen))
        tabLayout_plen.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_plen.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}