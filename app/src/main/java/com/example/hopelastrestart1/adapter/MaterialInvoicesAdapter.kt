package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Project
import com.example.hopelastrestart1.data.network.responses.Invoices
import kotlinx.android.synthetic.main.item_material_invoice.view.*
import kotlinx.android.synthetic.main.item_organization.view.*

class MaterialInvoicesAdapter(
    val invoice: List<Invoices>,
    private val cellClickListener: MyMaterialInvoiceClickListner
) : RecyclerView.Adapter<MaterialInvoicesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MaterialInvoicesAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_material_invoice, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return invoice.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.tv_material_id
        val addedBy = itemView.tv_material_addedBy

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = invoice[position]
        holder?.addedBy.text = item.added_by.toString()
        holder?.id.text = item.invoice_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item)
        }
    }
}

interface MyMaterialInvoiceClickListner {
    fun onCellClickListener(
        invoice: Invoices,

        )
}

