package com.example.hopelastrestart1.ui.siteEngineer

import RetrofitBuilder
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
import com.example.hopelastrestart1.databinding.ActivitySiteEngTaskPipeBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class SiteEngTaskpipeActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivitySiteEngTaskPipeBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngTaskPipeBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Reports"
        //    binding = DataBindingUtil.setContentView(this, R.layout.activity_taskpipe)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)          //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val task_name = intent.getStringExtra("task_name")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        //get the existing values
        /*  val pipeAct by lazyDeferred {
              viewModel.activitiespipe(username, organization_name, project_name, plan_id, task_id, activity_name)
          }
          Coroutines.main {
              progress_bar.show()
              val orgs = pipeAct.await()
              orgs.observe(this, Observer {
                  progress_bar.hide()
                  binding.editTextActivityName.setText(it.pipe_activity_name)
                  binding.editTextStartedOn.setText(it.started_on)
                  binding.editTextBedding.setText(it.bedding)
                  binding.editTextJointFilling.setText(it.back_filling)
                  binding.editTextLaying.setText(it.laying)
                  binding.editTextPipeStatus.setText(it.status)
                  binding.editTextPipelineTrench.setText(it.trenching_pipeline)
                  binding.editTextPipejointing.setText(it.pipe_jointing)
                  binding.editTextPipeStatus.setText(it.status)
                  // it.size.toString()
                  // initRecyclerView(it.toOrganizationItem())
              })
          }*/
        binding.buttonSubmit.setOnClickListener {
            val activity_name = binding.editTextActivityName.text.toString().trim()
            val trenching_pipeline = binding.editTextPipelineTrench.text.toString().trim()
            val bedding = binding.editTextBedding.text.toString().trim()
            val joint_filling = binding.editTextJointFilling.text.toString().trim()

            val laying = binding.editTextLaying.text.toString().trim()
            val back_filling = binding.backFilling.text.toString().trim()
            val bottom_trench = binding.editBottomTrench.text.toString().trim()
            val midtrench = binding.editMidtrench.text.toString().trim()
            val toptrench = binding.editToptrench.text.toString().trim()
            val consolidation = binding.editConsolidation.text.toString().trim()
            val pipe_jointing = binding.editTextPipejointing.text.toString().trim()
            val start_on = binding.editTextStartedOn.text.toString().trim()
            val pipe_status = binding.editTextPipeStatus.text.toString().trim()
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!


            val pipeline = PipeWork(
                assignedTask.activity_task_id.toString(),
                assignedTask.activity_name.toString(),
                trenching_pipeline,
                bedding,
                laying,
                back_filling,
                bottom_trench,
                midtrench,
                toptrench,
                consolidation,
                binding.checkboxRemovalExcessSoil.isChecked
            )


            /* val Gson = Gson()
             val json = Gson.toJson(pipeline)
             val obj = JSONObject(json)
             val skill = Skill(obj)*/
            val oMapper = ObjectMapper()
            val ccc: MutableMap<*, *>? = oMapper.convertValue(pipeline, MutableMap::class.java)
            val skill = TaskWork(ccc as HashMap<String, String>)
            val submitMhReport = SubmitPTaskReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled!!.organization_name,
                assignedTask.work?.skilled!!.project_name,
                assignedTask.work?.skilled!!.plan_name,
                assignedTask.work?.skilled!!.task_name,
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                assignedTask.activity_type.toString(),
                skill, assignedTask.activity_name.toString(),
            )
            submitPipeReport(submitMhReport)

            /*  //    updateActivity(username, organization_name, project_name, plan_id, task_id, activity_id)
  //ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
              val intent = Intent(this, ActivitiesActivity::class.java)
              intent.putExtra("username", username)

              startActivity(intent)*/
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun submitPipeReport(submitTaskReport: SubmitPTaskReport) {
        viewModel.submitPWorkReport(submitTaskReport).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
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
