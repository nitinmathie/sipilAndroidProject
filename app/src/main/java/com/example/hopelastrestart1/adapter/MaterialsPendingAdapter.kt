package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.network.responses.GetAssignedMachinery
import com.example.hopelastrestart1.data.network.responses.MaterialDispatchResp
import kotlinx.android.synthetic.main.item_material_disp.view.*
import kotlinx.android.synthetic.main.item_material_pending.view.*

class MaterialsPendingAdapter(
    val matDisp: List<GetAssignedMachinery>,
    private val cellClickListenerMatPending: CellClickListenerMatPending,
    val username: String,
    val organization_name: String,
    val project_name: String
) : RecyclerView.Adapter<MaterialsPendingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MaterialsPendingAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_material_pending, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return matDisp.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status = itemView.tv_act_id
        val dispto = itemView.tv_act_assignedto
        val dispby = itemView.tv_act_assignedby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = matDisp[position]
        holder?.status.text = item.assigned_activity_id
        holder?.dispby.text = item.activity_assigned_by
        holder?.dispto.text = item.activity_assigned_to

        holder.itemView.setOnClickListener {
            cellClickListenerMatPending.cellClickListenerMatPending(item, username, "", "")
        }
        /*  holder.itemView.setOnClickListener {
              var intent = Intent(context, TaskActivity::class.java)
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //    GlobalData.getInstance.planName = item.plan_name
              context.startActivity(intent)
              //cellClickListenerPlan.onCellClickListenerPlan(item, username, organization_name, project_name)
          }*/
    }


}

interface CellClickListenerMatPending {
    fun cellClickListenerMatPending(
        getAssignedMachinery: GetAssignedMachinery,
        username: String,
        organization_name: String,
        project_name: String
    )


}
