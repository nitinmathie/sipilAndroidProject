package com.example.hopelastrestart1.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.model.MachinesQuantity
import com.example.hopelastrestart1.model.MachinesStartAndEndTime
import kotlinx.android.synthetic.main.item_machines_and_material.view.*
import kotlinx.android.synthetic.main.item_machines_and_material.view.img_close
import kotlinx.android.synthetic.main.item_machines_and_material.view.tv_machineName
import kotlinx.android.synthetic.main.layout_start_end_time.view.*

class MachinesSTandETAdapter(
    val machines: List<MachinesStartAndEndTime>,
    private val cellClickListener: CellClickListenerSTandET,
    val username: String
) : RecyclerView.Adapter<MachinesSTandETAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MachinesSTandETAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_start_end_time, parent, false)
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
        val startTime = itemView.tv_start_time
        val endTime = itemView.tv_end_time
        val close = itemView.img_close

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = machines[position]

        holder?.name.text = item.machineName.toString()
        holder?.startTime.text = item.startTime.toString()
        holder?.endTime.text = item.endTime.toString()
        holder.close.setOnClickListener {
            cellClickListener.onCellClickListener(item, username)
        }


    }

    interface CellClickListenerSTandET {
        fun onCellClickListener(machines: MachinesStartAndEndTime, username: String)
    }
}

