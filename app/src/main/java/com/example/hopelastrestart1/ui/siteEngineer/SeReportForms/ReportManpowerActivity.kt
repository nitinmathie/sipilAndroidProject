package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAssignTaskManpowerBinding
import com.example.hopelastrestart1.databinding.ActivityReportManpowerBinding
import com.example.hopelastrestart1.model.SubmitMachineryReport
import com.example.hopelastrestart1.model.SubmitManPowerReport
import com.example.hopelastrestart1.model.SubmitMaterialReport
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.view.BaseActivity
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportManpowerActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportManpowerBinding
    private lateinit var viewModel: com.example.hopelastrestart1.viewmodel.TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityReportManpowerBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Assign Task"
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(com.example.hopelastrestart1.viewmodel.TaskViewModel::class.java)

        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        val skilled_labour = binding.editTextSkilledLabour.text.toString().trim()

        binding.btnSubmitReport.setOnClickListener {
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val jsonObject = JSONObject()
            val manpower = JSONObject()
            manpower.put("count", binding.etSkilledCount.text.toString())
            manpower.put("hours", binding.etHours.text.toString())
            jsonObject.put("skilled", manpower)
            val mapowerUnskilled = JSONObject()
            mapowerUnskilled.put("count", binding.etUnskilledCount.text.toString())
            mapowerUnskilled.put("hours", binding.etUnskilledHours.text.toString())
            jsonObject.put("unskilled", mapowerUnskilled)


            val submitMachine = SubmitManPowerReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled?.org_name.toString(),
                assignedTask.work?.skilled?.project_name.toString(),
                assignedTask.work?.skilled?.plan_name.toString(),
                assignedTask.work?.skilled?.task_name.toString(),
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                jsonObject, assignedTask.activity_type.toString()
            )
            submitManPowerReport(submitMachine)

        }


    }

    private fun submitManPowerReport(submitManPowerReport: SubmitManPowerReport) {
        viewModel.submitManPowerReport(submitManPowerReport).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                            // val intent = Intent(this, ActivitiesActivity::class.java)
                            /*    intent.putExtra("username", username)
                                intent.putExtra("organization_name", organization_name)
                                intent.putExtra("project_name", project_name)
                                intent.putExtra("plan_name", plan_name)
                                intent.putExtra("task_name", task_name)*/
                            //    startActivity(intent)
                            finish()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })

    }
}