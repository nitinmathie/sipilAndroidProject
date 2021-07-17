package com.example.hopelastrestart1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.model.DPRs
import com.example.hopelastrestart1.model.Report
import com.example.hopelastrestart1.model.Units
import kotlinx.android.synthetic.main.fragment_planner_dashboard.view.*
import kotlinx.android.synthetic.main.item_dpr_reports.view.*
import kotlinx.android.synthetic.main.item_organization.view.*
import kotlinx.android.synthetic.main.item_reports.view.*

class AdminDFRsAdapter(
    val reports: MutableList<Units>,
    private val cellClickListener: CellClickListenerAdminDFRsReports,
    val loginType: String,
) : RecyclerView.Adapter<AdminDFRsAdapter.RowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AdminDFRsAdapter.RowViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dpr_reports, parent, false)
        return RowViewHolder(v)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.header_bag)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.content_bag)
    }

    override fun getItemCount(): Int {
        return reports.size + 1
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    /*   class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           val name = itemView.activity_name
           val units = itemView.unit
           val quantity = itemView.qunatitiy
           val price = itemView.price
           *//* val approve_report = itemView.approve_report
         val reject_report = itemView.reject_report
         val ll_apporve_report = itemView.ll_apporve_report*//*

    }*/

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {

        val rowPos = holder.adapterPosition
        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.itemView.apply {
                setHeaderBg(activity_name)
                setHeaderBg(qunatitiy)
                setHeaderBg(unit)
                // setHeaderBg(price)

                activity_name.text = "Date"
                qunatitiy.text = "Activity"
                unit.text = "Quantity"
                //   price.text = "Price"   
            }
        } else {
            val item = reports[rowPos - 1]

            holder.itemView.apply {
                setContentBg(activity_name)
                setContentBg(qunatitiy)
                setContentBg(unit)
                // setContentBg(price)

                activity_name.text = item.name
                unit.text = item.unit
                qunatitiy.text = item.quantity
                //  price.text = item.price
            }


            /* val item = reports[position]
             holder?.quantity.text = item.quantity.toString()
             holder?.units.text = item.unit.toString()
 */
            holder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(item, loginType, "onclick")
            }
        }
    }

    interface CellClickListenerAdminDFRsReports {
        fun onCellClickListener(unit: Units, loginType: String, approveType: String)
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}


