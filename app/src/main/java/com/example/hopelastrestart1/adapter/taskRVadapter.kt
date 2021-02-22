package com.example.hopelastrestart1.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Task
import kotlinx.android.synthetic.main.item_task.view.*

class taskRVAdapter(val tasks: List<Task>,
                    private val cellClickListener: CellClickListener_task,
                    val username:String, val organization_name: String,
                    val project_name:String, val plan_name: String):RecyclerView.Adapter<taskRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : taskRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
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
        val id = itemView.task_id
        val name = itemView.startnode

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder?.name.text = item.task_name.toString()
        holder?.id.text = item.task_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username, organization_name, project_name, plan_name)
        }
    }
}
interface  CellClickListener_task {
    fun onCellClickListener(task: Task, username: String, organization_name: String, project_name: String, plan_name: String)
}

