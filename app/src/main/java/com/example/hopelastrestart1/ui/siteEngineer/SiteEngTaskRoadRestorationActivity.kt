package com.example.hopelastrestart1.ui.siteEngineer

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
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivitySiteEngTaskRoadReBinding
import com.example.hopelastrestart1.databinding.ActivityTaskRoadRestorationBinding
import com.example.hopelastrestart1.databinding.ActivityTaskpipeBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SiteEngTaskRoadRestorationActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivitySiteEngTaskRoadReBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngTaskRoadReBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)

        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val task_name = intent.getStringExtra("task_name")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")

        binding.buttonUpdateRrActivity.setOnClickListener {

            val actvitityName = binding.activityName.text.toString().trim()
            val pipeTLength = binding.editTrenchingLengthPipe.text.toString().trim()
            val pipeTWidth = binding.etTrenchingWidthPipe.text.toString().trim()
            val pipeFillWithDust = binding.pipeFillWithDust.isChecked
            val pccPipeLength = binding.etPpcPipeTl.text.toString().trim()
            val pccPipeWidth = binding.etPpcPipeTw.text.toString().trim()
            val pccPipeDepth = binding.etPpcPipeTd.text.toString().trim()
            val vccPipeTl = binding.etVccPipeTl.text.toString().trim()
            val vccPipeTw = binding.etVccPipeTw.text.toString().trim()
            val vccPipeTd = binding.etVccPipeTd.text.toString().trim()
            val upvcPipeTl = binding.etUpvcPipeTl.text.toString().trim()
            val upvcPipeTw = binding.etUpvcPipeTw.text.toString().trim()
            val upvcPipeTd = binding.etUpvcPipeTd.text.toString().trim()
            val upvcFillWithDest = binding.cbUpvcFillWithDest.isChecked
            val upvcVccPipeTl = binding.etUpvcVccPipeTl.text.toString().trim()
            val upvcVccPipeTw = binding.etUpvcVccPipeTw.text.toString().trim()
            val upvcVccPipeTd = binding.etUpvcVccPipeTd.text.toString().trim()

            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val rrWork = RRWork(
                assignedTask.activity_task_id.toString(),
                assignedTask.activity_name.toString(),
                pipeTLength,
                pipeTWidth,
                pipeFillWithDust,
                pccPipeLength,
                pccPipeWidth,
                pccPipeDepth,
                vccPipeTl,
                vccPipeTw,
                vccPipeTd,
                upvcPipeTl,
                upvcPipeTw,
                upvcFillWithDest,
                upvcVccPipeTl,
                upvcVccPipeTw,
                upvcVccPipeTd
            )
            val Gson = Gson()
            val json = Gson.toJson(rrWork)
            val obj = JSONObject(json)
            val skill = Skill(obj)

            val submitWorkReportModel = SubmitTaskReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                "", "", "", "",
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                assignedTask.activity_type.toString(),
                skill, assignedTask.activity_name.toString()
            )
            submitRoadReport(submitWorkReportModel)


        }

    }


    private fun submitRoadReport(submitTaskReport: SubmitTaskReport) {
        viewModel.submitWorkReport(submitTaskReport).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
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
