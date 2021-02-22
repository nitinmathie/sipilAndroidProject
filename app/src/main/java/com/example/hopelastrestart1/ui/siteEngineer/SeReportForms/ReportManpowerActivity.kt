package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityReportManpowerBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.util.lazyDeferred
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportManpowerActivity () : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportManpowerBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_manpower)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        val skilled_labour = binding.editTextSkilledLabour.text.toString().trim()
        val skilled_hours = binding.editTextSkilledWorktime.text.toString().trim()
        val unskilled_labour = binding.editTextUnskilledLabour.text.toString().trim()
        val unskilled_hours = binding.editTextUnskilledWorktime.text.toString().trim()
        binding.btnSubmitActivity.setOnClickListener {
            val tasks by lazyDeferred {
                viewModel.submitManpowerReport(
                    username, organization_name, project_name, plan_id, task_id, activity_id,
                    activity_name,
                    skilled_hours,
                    skilled_hours,
                    unskilled_labour,
                    unskilled_hours,
                )
            }
//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, SiteHomeActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }


    }
}