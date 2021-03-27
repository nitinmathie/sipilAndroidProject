package com.example.hopelastrestart1.ui.siteEngineer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.ccActivityAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivitySiteEngTaskccBinding
import com.example.hopelastrestart1.databinding.ActivityTaskBinding
import com.example.hopelastrestart1.databinding.ActivityTaskccBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.ActivitiesActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.activity_taskcc.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception


//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class SiteEngTaskccActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()

    private lateinit var binding: ActivitySiteEngTaskccBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngTaskccBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        //  binding = DataBindingUtil.setContentView(this, R.layout.activity_taskcc)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
            val activity_name = binding.editTextActivityName.text.toString().trim()
            val StartedOn = binding.editTextStartedOn.text.toString().trim()
            val pipeTrench = binding.editText500PipelineTrench.text.toString().trim()
            val cc_status = binding.editTextCcStatus.text.toString().trim()
            val Ic_150 = binding.editTextIc150.text.toString().trim()
            val mh_area = binding.editTextMharea.text.toString().trim()
            val upvc_300 = binding.editTextUpvc300.text.toString().trim()
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val ccWork = CCWork(
                assignedTask.activity_task_id.toString(),
                assignedTask.activity_name.toString(),
                pipeTrench,
                upvc_300,
                Ic_150,
                binding.checkboxManholeArea.isChecked
            )
            val Gson = Gson()
            val json = Gson.toJson(ccWork)
            val obj = JSONObject(json)
            val skill = Skill(obj)
            val submitWorkReportModel = SubmitTaskReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled?.org_name.toString(),
                assignedTask.work?.skilled?.project_name.toString(),
                assignedTask.work?.skilled?.plan_name.toString(),
                assignedTask.work?.skilled?.task_name.toString(),
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                assignedTask.activity_type.toString(),
                skill, assignedTask.activity_name.toString()
            )
            submitCCWorkReport(submitWorkReportModel)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun submitCCWorkReport(submitCCwork: SubmitTaskReport) {
        viewModel.submitWorkReport(submitCCwork).observe(this, Observer {
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
