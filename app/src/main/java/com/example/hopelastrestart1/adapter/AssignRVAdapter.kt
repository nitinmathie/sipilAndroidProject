package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.*
import kotlinx.android.synthetic.main.item_assign_task.view.*

class AssignRVAdapter(val tasks: List<AssignedByActivity>,
                      private val cellClickListener: CellClickListener_Assigntask,
                      val username:String, val organization_name: String,
                      val project_name:String, val plan_name: String):RecyclerView.Adapter<AssignRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AssignRVAdapter.ViewHolder {
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
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id = itemView.assigned_by
        val name = itemView.startnode

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder?.name.text = item.assigned_by.toString()
        holder?.id.text = item.assign_task_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username, organization_name, project_name, plan_name)
        }
    }
}
interface CellClickListener_Assigntask {
    fun onCellClickListener(task: AssignedByActivity, username: String, organization_name: String, project_name: String, plan_name: String)
}


