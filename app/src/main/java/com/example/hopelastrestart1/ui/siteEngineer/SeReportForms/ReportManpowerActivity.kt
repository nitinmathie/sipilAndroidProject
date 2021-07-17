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
import com.example.hopelastrestart1.model.*
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
        val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
        binding.tvSkilledCount.text = assignedTask.manpower!!.skilled
        binding.tvUnskilledCount.text = assignedTask.manpower!!.unskilled

        binding.btnSubmitReport.setOnClickListener {
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val jsonObject = JSONObject()
            val manpower = JSONObject()

            val sk = Sk(binding.etSkilledCount.text.toString(), binding.etHours.text.toString())
            val usk = USk(
                binding.etUnskilledCount.text.toString(),
                binding.etUnskilledHours.text.toString()
            )
            val mp = MP(sk, usk)


            val submitMachine = SubmitManPowerReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled!!.organization_name,
                assignedTask.work?.skilled!!.project_name,
                assignedTask.work?.skilled!!.plan_name,
                assignedTask.work?.skilled!!.task_name,
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                mp, assignedTask.activity_name.toString()
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