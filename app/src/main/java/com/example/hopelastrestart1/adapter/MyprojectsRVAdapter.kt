package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Project
import kotlinx.android.synthetic.main.item_organization.view.*

class MyprojectsRVAdapter(
    val projects: List<com.example.hopelastrestart1.model.Project>,
    private val cellClickListener: MyProjectsItemCellListener
) : RecyclerView.Adapter<MyprojectsRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MyprojectsRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.name
        val name = itemView.createdby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = projects[position]
        holder?.name.text = item.project_name.toString()
        holder?.id.text = item.project_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item)
        }
    }
}

interface MyProjectsItemCellListener {
    fun onCellClickListener(
        project: com.example.hopelastrestart1.model.Project,

        )
}

