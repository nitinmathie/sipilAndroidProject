package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Plan
import kotlinx.android.synthetic.main.item_plan.view.*

class InvoicesAdapter(
   // val plans: List<Invoices>,
    private val cellClickListenerPlan: CellClickListenerInvoice,
    val username: String,
    val organization_name: String,
    val project_name: String
) : RecyclerView.Adapter<InvoicesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : InvoicesAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plan, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 10
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
       // val item = plans[position]
        holder?.name.text = "user@gmail.com"
        holder?.id.text = "1"

      /*  holder.itemView.setOnClickListener {
            var intent = Intent(context, TaskActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //    GlobalData.getInstance.planName = item.plan_name
            context.startActivity(intent)
            //cellClickListenerPlan.onCellClickListenerPlan(item, username, organization_name, project_name)
        }*/
    }


}

interface CellClickListenerInvoice {
    fun CellClickListenerInvoice(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    )


}
