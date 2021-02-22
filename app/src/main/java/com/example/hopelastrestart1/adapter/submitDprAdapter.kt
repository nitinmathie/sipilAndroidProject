package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.*

import kotlinx.android.synthetic.main.item_organization.view.*
class submitDprAdapter(
    val tasks: List<DPR>,
    private val cellClickListener: CellClickListener_SubmitDpr,
    val username:String, val organization_name: String,
    val project_name:String, val plan_name: String):RecyclerView.Adapter<submitDprAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
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


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder?.name.text = item.activity_type_id.toString()
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

    interface CellClickListener_SubmitDpr {
        fun onCellClickListener(
            task: DPR,
            username: String,
            organization_name: String,
            project_name: String,
            plan_name: String
        )
    }
}


