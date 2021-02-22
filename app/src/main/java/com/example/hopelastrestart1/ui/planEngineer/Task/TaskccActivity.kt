


package com.example.hopelastrestart1.ui.planEngineer.Task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityTaskccBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class TaskccActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskccBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_taskcc)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val plan_id = intent.getStringExtra("plan_name")
        val activity_name = intent.getStringExtra("activity_name")
        val x = 0
        //
        val ccAct by lazyDeferred {
            viewModel.activitiescc(
                username,
                organization_name,
                project_name,
                plan_id,
                task_id,
                activity_name
            )
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = ccAct.await()
            orgs.observe(this, Observer {
                progress_bar.hide()
                val x = it
                binding.editTextActivityName.setText(it.ccbreaking_activity_name)
                binding.editTextStartedOn.setText(it.started_on)
                binding.editText500PipelineTrench.setText(it.ccb_pipeline_trench_500_status)
                binding.editTextCcStatus.setText(it.status)
                binding.editTextIc150.setText(it.ccb_IC_500)
                binding.editTextMharea.setText(it.ccb_mharea_status)
                binding.editTextUpvc300.setText(it.ccb_upvc_350)
            })
        }
        //
        binding.buttonUpdateCcActivity.setOnClickListener {

            val activity_name = binding.editTextActivityName.text.toString().trim()
            val StartedOn = binding.editTextStartedOn.text.toString().trim()
            val pipeTrench = binding.editText500PipelineTrench.text.toString().trim()
            val cc_status = binding.editTextCcStatus.text.toString().trim()
            val Ic_150 = binding.editTextIc150.text.toString().trim()
            val mh_area = binding.editTextMharea.text.toString().trim()
            val upvc_300 = binding.editTextUpvc300.text.toString().trim()

            updateActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, TaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
            intent.putExtra("task_id", task_id)
            intent.putExtra("activity_name", activity_name)
            startActivity(intent)
        }
        binding.buttonAssignCcActivity.setOnClickListener {
            val intent = Intent(this, AssignTaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
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
        activity_name: String
    ){
       // val activity_name = binding.editTextActivityName.text.toString().trim()
        val pipeline_status = binding.editText500PipelineTrench.text.toString().trim()
        val ic_status = binding.editTextIc150.text.toString().trim()
        val upvc_status = binding.editTextUpvc300.text.toString().trim()
        val mh_area = binding.editTextMharea.text.toString().trim()
        val start_on = binding.editTextStartedOn.text.toString().trim()
        val cc_status = binding.editTextCcStatus.text.toString().trim()
        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()
               lifecycleScope.launch {
            try {
                val taskResponse = viewModel.updateCCActivity(
                    pipeline_status,
                    ic_status,
                    upvc_status,
                    mh_area,
                    cc_status,
                    activity_name,
                    task_id,
                    plan_id,
                    project_name,
                    organization_name,
                    username
                )


            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
}
