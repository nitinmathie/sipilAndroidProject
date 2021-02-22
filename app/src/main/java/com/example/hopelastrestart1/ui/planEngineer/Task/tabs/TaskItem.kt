package com.example.hopelastrestart1.ui.planEngineer.Task.tabs

import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.databinding.ItemTaskBinding
import com.xwray.groupie.databinding.BindableItem

class TaskItem(private val task : Task): BindableItem<ItemTaskBinding>()  {
    override fun bind(viewBinding: ItemTaskBinding, position: Int) {
        viewBinding.setOrg(task)
    }
    override fun getLayout()= R.layout.item_task
}