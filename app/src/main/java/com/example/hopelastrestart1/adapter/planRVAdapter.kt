package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanFragment
import kotlinx.android.synthetic.main.item_plan.view.*

class PlanAdapter(
    val plans: List<Plan>,
    private val cellClickListenerPlan: PlanFragment,
    val username:String,
    val organization_name:String,
    val project_name:String): RecyclerView.Adapter<PlanAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PlanAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plan, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return plans.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id = itemView.name
        val name = itemView.createdby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = plans[position]
        holder?.name.text = item.plan_name.toString()
        holder?.id.text = item.plan_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListenerPlan.onCellClickListener(item, username, organization_name, project_name)
        }
    }
}

interface  CellClickListenerPlan {
    fun onCellClickListener(plan: Plan, username: String, organization_name: String, project_name: String)
}
