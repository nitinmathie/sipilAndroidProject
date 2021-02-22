package com.example.hopelastrestart1.ui.planEngineer.Task.tabs

import com.example.hopelastrestart1.R

import com.example.hopelastrestart1.data.db.entities.AssignedToMeEntity
import com.example.hopelastrestart1.databinding.ItemAssignToTaskBinding
import com.xwray.groupie.databinding.BindableItem
class AssignedToTaskItem(private val task : AssignedToMeEntity): BindableItem<ItemAssignToTaskBinding>()  {
    override fun bind(viewBinding: ItemAssignToTaskBinding, position: Int) {
        viewBinding.setOrg(task)
    }
    override fun getLayout()= R.layout.item_assign_to_task
}

