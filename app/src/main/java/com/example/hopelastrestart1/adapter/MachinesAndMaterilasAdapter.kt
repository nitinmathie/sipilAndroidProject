package com.example.hopelastrestart1.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.model.MachinesQuantity
import kotlinx.android.synthetic.main.item_machines_and_material.view.*

class MachinesAndMaterilasAdapter(
    val machines: List<MachinesQuantity>,
    private val cellClickListener: CellClickListenerMachines,
    val username: String
) : RecyclerView.Adapter<MachinesAndMaterilasAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MachinesAndMaterilasAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_machines_and_material, parent, false)
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
        val close = itemView.img_close

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = machines[position]

        holder?.name.text = item.machineName.toString()
        holder?.quantity.text = item.quantity.toString()
        if (username.equals("updated")) {
            holder.close.visibility = View.INVISIBLE
            holder.close.setOnClickListener {
                cellClickListener.onCellClickListener(item, username)
            }
        } else {
            holder.close.visibility = View.VISIBLE
            holder.close.setOnClickListener {
                cellClickListener.onCellClickListener(item, username)
            }
        }

    }

    interface CellClickListenerMachines {
        fun onCellClickListener(machines: MachinesQuantity, username: String)
    }
}

