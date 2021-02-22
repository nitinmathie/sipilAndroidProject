package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.*

import kotlinx.android.synthetic.main.item_organization.view.*
class AssignToRVAdapter(
    val tasks: List<AssignedToMeEntity>,
    private val cellClickListener: CellClickListener_AssignTotask,
    val username:String, val organization_name: String,
    val project_name:String, val plan_name: String):RecyclerView.Adapter<AssignToRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AssignToRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_assign_task, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tasks.size
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



    interface CellClickListener_AssignTotask {
        fun onCellClickListener(
            task: AssignedToMeEntity,
            username: String,
            organization_name: String,
            project_name: String,
            plan_name: String
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder?.name.text = item.activity_name.toString()
        holder?.id.text = item.estimated_timeline.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(
                item,
                username,
                organization_name,
                project_name,
                plan_name
            )
        }
    }
}
interface CellClickListener_AssignDpr {
    fun onCellClickListener(task: AssignedToActivity, username: String, organization_name: String, project_name: String, plan_name: String)
}


