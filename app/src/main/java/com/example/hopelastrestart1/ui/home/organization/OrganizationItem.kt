package com.example.hopelastrestart1.ui.home.organization

import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.databinding.ItemOrganizationBinding
import com.xwray.groupie.databinding.BindableItem

class OrganizationItem(private val organization : Organization): BindableItem<ItemOrganizationBinding>() {
    override fun bind(viewBinding: ItemOrganizationBinding, position: Int) {
        viewBinding.setOrg(organization)
    }

    override fun getLayout()= R.layout.item_organization

}