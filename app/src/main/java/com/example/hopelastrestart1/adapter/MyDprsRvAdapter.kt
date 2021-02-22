
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
import com.example.hopelastrestart1.data.db.entities.AssignedToMeEntity
import com.example.hopelastrestart1.data.db.entities.DprInfo
import com.example.hopelastrestart1.data.network.responses.AssignedToMe
import kotlinx.android.synthetic.main.item_activities.view.*
import kotlinx.android.synthetic.main.item_organization.view.name


class MyDprsRvAdapter(
    val allActs: List<DprInfo>,
    val mctx: Context,
    val cellClickListener: CellClickListener_MyDpr,
    val username: String,
    val organization_name: String,
    val project_name: String,

    ):RecyclerView.Adapter<MyDprsRvAdapter.ViewHolder>(){

    // private val mCtx: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_assigned_to_me, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allActs.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val id = itemView.name
        val name = itemView.type
        val buttonViewOption = itemView.textViewOptions
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allActs[position]
        holder?.name.text = item.dpr_id.toString()
        holder?.id.text = item.task_id.toString()
        holder.buttonViewOption.setOnClickListener(View.OnClickListener {
            //creating a popup menu
//issue with context
            //creating a popup menu
            val popup = PopupMenu(mctx, holder.buttonViewOption)
            //inflating menu from xml resource
            //inflating menu from xml resource
            popup.inflate(R.menu.assignedtome_rv_menu)
            //adding click listener
            //adding click listener
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(i: MenuItem): Boolean {
                    when (i.getItemId()) {
                        R.id.view_activity -> {
                            cellClickListener.onCellClickListener(
                                item,
                                username,
                                organization_name,
                                project_name,

                                0,
                            )
                        }
                        R.id.submit_machine_dpr -> {
                            cellClickListener.onCellClickListener(
                                item,
                                username,
                                organization_name,
                                project_name,

                                1
                            )
                        }
                        R.id.submit_Material_dpr -> {
                            cellClickListener.onCellClickListener(
                                item,
                                username,
                                organization_name,
                                project_name,

                                2
                            )
                        }
                        R.id.submit_worker_dpr -> {
                            cellClickListener.onCellClickListener(
                                item,
                                username,
                                organization_name,
                                project_name,

                                3
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

interface  CellClickListener_MyDpr {
    // abstract val activity: Context?
    fun onCellClickListener(
        activity: DprInfo, username: String,
        organization_name: String,
        project_name: String,
        assign: Int

    )

}