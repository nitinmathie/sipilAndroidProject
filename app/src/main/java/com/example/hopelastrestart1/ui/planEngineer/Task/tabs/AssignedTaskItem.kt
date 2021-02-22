package com.example.hopelastrestart1.ui.planEngineer.Task.tabs
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.AssignedByActivity
import com.example.hopelastrestart1.databinding.ItemAssignTaskBinding
import com.xwray.groupie.databinding.BindableItem
class AssignedTaskItem(private val task : AssignedByActivity): BindableItem<ItemAssignTaskBinding>()  {
        override fun bind(viewBinding: ItemAssignTaskBinding, position: Int) {
            viewBinding.setOrg(task)
        }
        override fun getLayout()= R.layout.item_assign_task
    }
