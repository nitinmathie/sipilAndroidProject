package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.data.db.entities.Task
import kotlinx.android.synthetic.main.item_task.view.*

class InvoicesRVAdapter(val invoices: List<Invoice>,
                    private val cellClickListener: CellClickListener_invoice,
                    val username:String, val organization_name: String,
                    val project_name:String, val store_name: String):RecyclerView.Adapter<InvoicesRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : InvoicesRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_added_inventory, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return invoices.size
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
        val item = invoices[position]
        holder?.name.text = item.date_of_entry.toString()
        holder?.id.text = item.invoice_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username, organization_name, project_name, store_name)
        }
    }
}
interface  CellClickListener_invoice {
    fun onCellClickListener(invoice: Invoice, username: String, organization_name: String, project_name: String, store_name: String)
}