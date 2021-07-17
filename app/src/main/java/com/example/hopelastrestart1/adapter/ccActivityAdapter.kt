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
import kotlinx.android.synthetic.main.item_activities.view.*
import kotlinx.android.synthetic.main.item_organization.view.name


class ccActivityAdapter(
    val allActs: List<Activit>,
    val mctx: Context,
    val cellClickListener: CellClickListener_CcAct,
) : RecyclerView.Adapter<ccActivityAdapter.ViewHolder>() {

    // private val mCtx: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activities, parent, false)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id = itemView.name
        val name = itemView.type
        val buttonViewOption = itemView.textViewOptions
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allActs[position]
        holder?.name.text = item.activity_name.toString()
        holder?.id.text = item.activity_type.toString()
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(
                item,
                false
            )


        }

        /*   holder.buttonViewOption.setOnClickListener(View.OnClickListener {
               //creating a popup menu
   //issue with context
               //creating a popup menu
               val popup = PopupMenu(mctx, holder.buttonViewOption)
               //inflating menu from xml resource
               //inflating menu from xml resource
               popup.inflate(R.menu.activity_rv_menu)
               //adding click listener
               //adding click listener
               popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                   override fun onMenuItemClick(i: MenuItem): Boolean {
                       when (i.getItemId()) {
                           R.id.assign -> {
                               cellClickListener.onCellClickListener(
                                   item,
                                   username,
                                   organization_name,
                                   project_name,
                                   plan_id,
                                   task_id,
                                   from_node, to_node,
                                   true,
                               )
                           }
                           R.id.update -> {
                               cellClickListener.onCellClickListener(
                                   item,
                                   username,
                                   organization_name,
                                   project_name,
                                   plan_id,
                                   task_id,
                                   from_node, to_node,
                                   false
                               )
                           }

                       }
                       return false
                   }
               })
               //displaying the popup
               //displaying the popup
               popup.show()

           })*/

    }
}

interface CellClickListener_CcAct {
    // abstract val activity: Context?
    fun onCellClickListener(
        activity: Activit,
        assign: Boolean

    )

}