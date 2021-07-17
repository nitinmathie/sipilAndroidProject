package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.model.Report
import kotlinx.android.synthetic.main.fragment_planner_dashboard.view.*
import kotlinx.android.synthetic.main.item_organization.view.*
import kotlinx.android.synthetic.main.item_reports.view.*

class ReportsAdapter(
    val reports: List<Report>,
    private val cellClickListener: CellClickListenerReports,
    val loginType: String,
) : RecyclerView.Adapter<ReportsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ReportsAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reports, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.report_name
        val type = itemView.report_type
        val approve_report = itemView.approve_report
        val reject_report = itemView.reject_report
        val ll_apporve_report = itemView.ll_apporve_report

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reports[position]
        holder?.name.text = item.activity_name.toString()
        holder?.type.text = item.report_type.toString()

        holder.approve_report.setOnClickListener {
            cellClickListener.onCellClickListener(item, loginType, "approve")
        }
        holder.reject_report.setOnClickListener {
            cellClickListener.onCellClickListener(item, loginType, "reject")
        }
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, loginType, "onclick")
        }
    }
}

interface CellClickListenerReports {
    fun onCellClickListener(reports: Report, loginType: String, approveType: String)
}


