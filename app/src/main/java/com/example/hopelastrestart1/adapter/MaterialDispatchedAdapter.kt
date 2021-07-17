package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.network.responses.MaterialDispatchResp
import kotlinx.android.synthetic.main.item_material_disp.view.*

class MaterialDispatchedAdapter(
    val matDisp: List<MaterialDispatchResp>,
    private val cellClickListenerPlan: CellClickListenerMatDisp,
    val username: String,
    val organization_name: String,
    val project_name: String
) : RecyclerView.Adapter<MaterialDispatchedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MaterialDispatchedAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_material_disp, parent, false)
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
        val status = itemView.tv_status
        val dispto = itemView.tv_dispatched_to
        val dispby = itemView.tv_disp_by

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = matDisp[position]
        holder?.status.text = item.status
        holder?.dispby.text = item.dispatched_by
        holder?.dispto.text = item.dispatched_to

        /*  holder.itemView.setOnClickListener {
              var intent = Intent(context, TaskActivity::class.java)
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //    GlobalData.getInstance.planName = item.plan_name
              context.startActivity(intent)
              //cellClickListenerPlan.onCellClickListenerPlan(item, username, organization_name, project_name)
          }*/
    }


}

interface CellClickListenerMatDisp {
    fun CellClickListenerMatDisp(
        materialDispatchResp: MaterialDispatchResp,
        username: String,
        organization_name: String,
        project_name: String
    )


}
