package com.example.hopelastrestart1.ui.planEngineer.Task

import com.example.hopelastrestart1.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityTaskccBinding
import com.example.hopelastrestart1.databinding.ActivityTaskpipeBinding
import com.example.hopelastrestart1.model.UpdatePipelineActivity
import com.example.hopelastrestart1.model.UpdateTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class TaskpipeActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskpipeBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskpipeBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

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
        binding.buttonUpdatePipeActivity.setOnClickListener {
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
            val updateActivity = UpdatePipelineActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
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
            updateTaskPipeActivity(updateActivity)

            /*  //    updateActivity(username, organization_name, project_name, plan_id, task_id, activity_id)
  //ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
              val intent = Intent(this, ActivitiesActivity::class.java)
              intent.putExtra("username", username)

              startActivity(intent)*/
        }
        binding.buttonAssignPipeActivity.setOnClickListener {
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
            val updateActivity = UpdatePipelineActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
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
            GlobalData.getInstance.updatePipelineActivity = updateActivity


            val intent = Intent(this, AssignTaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_name)
            intent.putExtra("task_id", task_id)
            intent.putExtra("activity_name", activity_name)
            startActivity(intent)
        }
    }

    private fun updateActivity(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_id: String
    ) {
        val activity_name = binding.editTextActivityName.text.toString().trim()
        val trenching_pipeline = binding.editTextPipelineTrench.text.toString().trim()
        val bedding = binding.editTextBedding.text.toString().trim()
        val joint_filling = binding.editTextJointFilling.text.toString().trim()

        val laying = binding.editTextLaying.text.toString().trim()
        val pipe_jointing = binding.editTextPipejointing.text.toString().trim()
        val start_on = binding.editTextStartedOn.text.toString().trim()
        val pipe_status = binding.editTextPipeStatus.text.toString().trim()


        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        /* lifecycleScope.launch {
             try {
                 val taskResponse = viewModel.updatePipeActivity(
                     activity_name,
                     trenching_pipeline,
                     bedding,
                     laying,
                     pipe_jointing,
                     joint_filling,
                     start_on,
                     pipe_status,
                     activity_id,
                     task_id,
                     plan_id,
                     project_name,
                     organization_name,
                     username
                 )

                 //call organization activity
                 val organizations by lazyDeferred {
                     viewModel.tasks1(username, organization_name, project_name, plan_id)
                 }
             } catch (e: ApiException) {
                 e.printStackTrace()
             } catch (e: NoInternetException) {
                 e.printStackTrace()
             }
         }*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun updateTaskPipeActivity(updateTaskActivity: UpdatePipelineActivity) {

        viewModel.updateTaskPipelineActivity(updateTaskActivity).observe(this, Observer {
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
