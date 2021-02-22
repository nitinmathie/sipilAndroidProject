
package com.example.hopelastrestart1.ui.planEngineer.Task.tabs
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.ReportedTask
import com.example.hopelastrestart1.databinding.ItemReportTaskBinding
import com.xwray.groupie.databinding.BindableItem
class ReportedTaskItem(private val task : ReportedTask): BindableItem<ItemReportTaskBinding>()  {
    override fun bind(viewBinding: ItemReportTaskBinding, position: Int) {
        viewBinding.setOrg(task)
    }
    override fun getLayout()= R.layout.item_report_task
}
