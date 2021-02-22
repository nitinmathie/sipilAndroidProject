package com.example.hopelastrestart1.ui.home.plen.tabs

import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.databinding.ItemOrganizationBinding
import com.example.hopelastrestart1.databinding.ItemPlanBinding
import com.xwray.groupie.databinding.BindableItem

class PlanItem(private val plan : Plan): BindableItem<ItemPlanBinding>() {
    override fun bind(viewBinding: ItemPlanBinding, position: Int) {
        viewBinding.setOrg(plan)
    }
    override fun getLayout() = R.layout.item_plan
}