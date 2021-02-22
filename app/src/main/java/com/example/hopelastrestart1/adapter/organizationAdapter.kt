package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Organization
import kotlinx.android.synthetic.main.item_organization.view.*

class OrganizationAdapter( val organizations: List<Organization>,
                           private val cellClickListener: CellClickListener,
val username:String):RecyclerView.Adapter<OrganizationAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : OrganizationAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_organization, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return organizations.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val id = itemView.name
        val name = itemView.createdby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = organizations[position]
        holder?.name.text = item.organization_name.toString()
        holder?.id.text = item.organization_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username)
        }
    }
}

interface  CellClickListener {
    fun onCellClickListener(organization: Organization, username: String)
}