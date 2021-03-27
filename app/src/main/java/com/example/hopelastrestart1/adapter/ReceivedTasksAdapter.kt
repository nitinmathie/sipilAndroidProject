package com.example.hopelastrestart1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.model.GetAssignedTaskActivitesModel
import kotlinx.android.synthetic.main.item_activities.view.*
import kotlinx.android.synthetic.main.item_activities.view.textViewOptions
import kotlinx.android.synthetic.main.item_organization.view.name
import kotlinx.android.synthetic.main.item_received_taks.view.*


class ReceivedTasksAdapter(
    val context: Context?,
    val allTasks: List<GetAssignedTaskActivitesModel>,
    val cellClickListener: CellClickListener_RT,


    ) : RecyclerView.Adapter<ReceivedTasksAdapter.ViewHolder>() {

    // private val mCtx: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_received_taks, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id = itemView.tv_re_task_name
        val name = itemView.tv_task_name
        val buttonViewOption = itemView.textViewOptions
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allTasks[position]
        holder?.name.text = item.activity_name.toString()
        holder?.id.text = item.activity_type.toString()
      /*  holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(
                item,
                item.activity_type.toString()


            )


        }*/

        holder.buttonViewOption.setOnClickListener(View.OnClickListener {
            //creating a popup menu
            //issue with context
            //creating a popup menu
            val popup = PopupMenu(context, holder.buttonViewOption)
            //inflating menu from xml resource
            //inflating menu from xml resource
            popup.inflate(R.menu.activity_submit_report_menu)
            //adding click listener
            //adding click listener
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(i: MenuItem): Boolean {
                    when (i.getItemId()) {
                        R.id.work_rep -> {
                            cellClickListener.onCellClickListener(
                                item,
                                item.activity_type!!,
                                "work"
                            )
                        }
                        R.id.machine_rep -> {
                            cellClickListener.onCellClickListener(
                                item,
                                item.activity_type!!
                                , "machine"
                            )
                        }

                        R.id.material_rep -> {

                            cellClickListener.onCellClickListener(
                                item,
                                item.activity_type!!, "material"
                            )
                        }
                        R.id.manpower_rep -> {

                            cellClickListener.onCellClickListener(
                                item,
                                item.activity_type!!,
                                "manpower"
                            )
                        }

                    }
                    return false
                }
            })
            //displaying the popup
            //displaying the popup
            popup.show()

        })

    }
}

interface CellClickListener_RT {
    // abstract val activity: Context?
    fun onCellClickListener(
        task: GetAssignedTaskActivitesModel, activityType: String,
        clickType: String

    )

}