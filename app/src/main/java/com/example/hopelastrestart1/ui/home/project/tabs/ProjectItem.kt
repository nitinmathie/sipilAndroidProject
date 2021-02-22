package com.example.hopelastrestart1.ui.home.project.tabs

import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Project
import com.example.hopelastrestart1.databinding.ItemOrganizationBinding
import com.example.hopelastrestart1.databinding.ItemProjectBinding
import com.xwray.groupie.databinding.BindableItem

class ProjectItem(private val project : Project): BindableItem<ItemProjectBinding>()  {
    override fun bind(viewBinding: ItemProjectBinding, position: Int) {
        viewBinding.setOrg(project)

    }
    override fun getLayout()= R.layout.item_project

}