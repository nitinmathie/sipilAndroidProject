package com.example.hopelastrestart1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.ui.home.plen.PlenActivity
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanFragment
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import kotlinx.android.synthetic.main.item_plan.view.*

class PlanAdapter(
    val context: Context,
    val plans: List<Plan>,
    private val cellClickListenerPlan: CellClickListenerPlan,
    val username: String,
    val organization_name: String,
    val project_name: String
) : RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.name
        val name = itemView.createdby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = plans[position]
        holder?.name.text = item.plan_name.toString()
        holder?.id.text = item.plan_id.toString()

        holder.itemView.setOnClickListener {
            var intent = Intent(context, TaskActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("plan_name", item.plan_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            context.startActivity(intent)
            //cellClickListenerPlan.onCellClickListenerPlan(item, username, organization_name, project_name)
        }
    }


}

interface CellClickListenerPlan {
    fun onCellClickListenerPlan(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    )


}
