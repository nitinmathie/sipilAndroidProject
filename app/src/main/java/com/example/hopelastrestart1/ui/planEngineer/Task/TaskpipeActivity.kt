
package com.example.hopelastrestart1.ui.planEngineer.Task
import com.example.hopelastrestart1.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.databinding.ActivityTaskpipeBinding
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
class TaskpipeActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskpipeBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_taskpipe)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        //get the existing values
        val pipeAct by lazyDeferred {
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
        }
        binding.buttonUpdatePipeActivity.setOnClickListener {
            updateActivity(username, organization_name , project_name, plan_id, task_id, activity_id)
//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, ActivitiesActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }
        binding.buttonAssignPipeActivity.setOnClickListener {
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
    private fun updateActivity(username:String, organization_name: String, project_name:String, plan_id: String, task_id: String, activity_id: String ){
        val activity_name = binding.editTextActivityName.text.toString().trim()
        val trenching_pipeline= binding.editTextPipelineTrench.text.toString().trim()
        val bedding = binding.editTextBedding.text.toString().trim()
        val joint_filling = binding.editTextJointFilling.text.toString().trim()

        val laying = binding.editTextLaying.text.toString().trim()
        val pipe_jointing = binding.editTextPipejointing.text.toString().trim()
        val start_on = binding.editTextStartedOn.text.toString().trim()

        val pipe_status = binding.editTextPipeStatus.text.toString().trim()

        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        lifecycleScope.launch {
            try {
                val taskResponse = viewModel.updatePipeActivity(
                    activity_name,trenching_pipeline,bedding,laying,pipe_jointing,joint_filling,start_on,pipe_status,activity_id,task_id, plan_id, project_name, organization_name, username)

                    //call organization activity
                    val organizations by lazyDeferred {
                        viewModel.tasks1(username, organization_name, project_name, plan_id)
                    }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
}
