package com.example.hopelastrestart1.adapter.fragmentAdapters

import AddedInventoryFragment
import IndentsReceivedFragment
import InventoryDashBoardFragment
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hopelastrestart1.ui.Inventory.inventoryManager.InventoryHomeActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.ui.siteEngineer.tabs.SeActivitiesFragment
import com.example.hopelastrestart1.ui.siteEngineer.tabs.SubmittedDprsFragment

class InventoryFragmentAdapter(
    inventoryHomeActivity: InventoryHomeActivity,
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
                    InventoryDashBoardFragment()
                }
                1 -> {
                    AddedInventoryFragment()
                    //recyclerview with all indents added through a form
                    // A form to fill in the received material information
                    // One material, Invoice, quantity, price, receipt, Po
                }
                2 -> {
                    IndentsReceivedFragment()
                    // these are the indents created in the backend when the s.e agrees to the material alloted to his task.
                    // a recyclerview
                    // a form displaying required material info.
                }
                3 ->{
                    //IndentsRequestedFragment()
                    InventoryDashBoardFragment()
                    // This doesn't exist for now this comes into play when the procurement team is added

                }
                else -> {
                    InventoryDashBoardFragment()
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
