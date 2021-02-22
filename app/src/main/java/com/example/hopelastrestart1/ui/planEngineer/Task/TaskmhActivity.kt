
package com.example.hopelastrestart1.ui.planEngineer.Task
import com.example.hopelastrestart1.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.databinding.ActivityTaskmhBinding
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
class TaskmhActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskmhBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_taskmh)
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
        binding.buttonUpdateMhActivity.setOnClickListener {
            updateActivity(username, organization_name , project_name, plan_id, task_id, activity_id)
//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            val intent = Intent(this, ActivitiesActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }
        binding.buttonAssignMhActivity.setOnClickListener {
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
        val dust_fill_PCC_below= binding.dustFillPCCBelow.text.toString().trim()
        val base_erection = binding.baseErection.text.toString().trim()
        val excavation = binding.editTextExcavation.text.toString().trim()
        val haunching = binding.editTextHaunching.text.toString().trim()
        val pipe_mhbase_connection = binding.editTextPipeMhbaseConnection.text.toString().trim()
        val back_filling = binding.editTextBackFilling.text.toString().trim()
        val cone_erection =  binding.coneErection.text.toString().trim()

        val removal_excess_soil = binding.editTextSoilRemoval.text.toString().trim()
        val raiser_erection = binding.editTextRaiserErection.text.toString().trim()
        val fix_UPVC = binding.editTextFixUPVC.text.toString().trim()
        val started_on = binding.editTextStartedOn.text.toString().trim()

        val mh_status = binding.editTextMhStatus.text.toString().trim()

        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        val mhAct by lazyDeferred {
            viewModel.activitiesmh(username, organization_name, project_name, plan_id, task_id, activity_name)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = mhAct.await()
            orgs.observe(this, Observer {
                progress_bar.hide()
                //Ensure the values are changed.
                binding.editTextActivityName.setText(it.pipe_activity_name)
                binding.editTextStartedOn.setText(it.started_on)
                binding.editTextBackFilling.setText(it.bedding)
                binding.editTextBaseErection.setText(it.back_filling)
                binding.editTextConeErection.setText(it.laying)
                binding.editTextDustFillPccBelow.setText(it.status)
                binding.editTextExcavation.setText(it.trenching_pipeline)
                binding.editTextFixUPVC.setText(it.pipe_jointing)
                binding.editTextSoilRemoval.setText(it.status)

                binding.editTextRaiserErection.setText(it.laying)
                binding.editTextMhStatus.setText(it.status)
                binding.editTextHaunching.setText(it.trenching_pipeline)
                binding.editTextPipeMhbaseConnection.setText(it.pipe_jointing)
                // it.size.toString()
                // initRecyclerView(it.toOrganizationItem())
            })
        }
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        lifecycleScope.launch {
            try {
                val taskResponse = viewModel.updateMHActivity(
                    activity_name,excavation,removal_excess_soil,dust_fill_PCC_below,base_erection,pipe_mhbase_connection,haunching,raiser_erection,cone_erection,fix_UPVC, back_filling,started_on, mh_status, activity_id,  task_id, plan_id, project_name, organization_name, username)

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


