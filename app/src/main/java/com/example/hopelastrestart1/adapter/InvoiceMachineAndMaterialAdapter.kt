package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.model.InvoiceMachines
import kotlinx.android.synthetic.main.item_added_machine_invoice.view.*
import kotlinx.android.synthetic.main.item_machines_and_material.view.img_close
import kotlinx.android.synthetic.main.item_machines_and_material.view.tv_machineName
import kotlinx.android.synthetic.main.item_machines_and_material.view.tv_quantity

class InvoiceMachineAndMaterialAdapter(
    val machines: List<InvoiceMachines>,
    private val cellClickListener: CellClickListenerInvoiceMachines,
    val username: String
) : RecyclerView.Adapter<InvoiceMachineAndMaterialAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : InvoiceMachineAndMaterialAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_added_machine_invoice, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return machines.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_machineName
        val quantity = itemView.tv_quantity
        val price = itemView.tv_price
        val ownership = itemView.tv_ownership
        val close = itemView.img_close

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = machines[position]

        if (username.equals("updated")) {
            holder?.name.text = item.machine_name.toString()
            holder?.quantity.text = item.machine_quantity.toString()
            holder?.price.text = item.machine_price.toString()
            holder?.ownership.text = item.machine_ownership.toString()
            holder.close.visibility = View.INVISIBLE
            holder.close.setOnClickListener {
                cellClickListener.CellClickListenerInvoiceMachines(item, username)
            }
        } else {
            holder?.name.text = item.machine_name.toString()
            holder?.quantity.text = item.machine_quantity.toString()
            holder?.price.text = item.machine_price.toString()
            holder?.ownership.text = item.machine_ownership.toString()
            holder.close.visibility = View.VISIBLE
            holder.close.setOnClickListener {
                cellClickListener.CellClickListenerInvoiceMachines(item, username)
            }
        }

    }

    interface CellClickListenerInvoiceMachines {
        fun CellClickListenerInvoiceMachines(machines: InvoiceMachines, username: String)
    }
}

