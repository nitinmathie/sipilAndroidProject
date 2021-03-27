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
import com.example.hopelastrestart1.databinding.ActivityTaskhscBinding
import com.example.hopelastrestart1.databinding.ActivityTaskpipeBinding
import com.example.hopelastrestart1.model.UpdateHscActivity
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
class TaskhscActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskhscBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskhscBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_taskhsc)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)


        //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        val task_name = intent.getStringExtra("task_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        //

        binding.buttonUpdateHscActivity.setOnClickListener {
            val hsc_activity_name = binding.editTextActivityName.text.toString().trim()
            val PCC_below_IC = binding.editTextPCCBelowIC.text.toString().trim()
            val erection_IC = binding.editTextErectionIC.text.toString().trim()
            val excavation_for_IC = binding.editTextIcExcavation.text.toString().trim()
            val upvc_ic_jointing = binding.editUpvcIcJointing.text.toString().trim()
            val provisions = binding.editProvisions.text.toString().trim()
            val dust_filling = binding.editTextDustFilling.text.toString().trim()
            val start_on = binding.editTextStartedOn.text.toString().trim()
            val hsc_status = binding.editTextCcStatus.text.toString().trim()
            val updateActivity = UpdateHscActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
                excavation_for_IC,
                PCC_below_IC,
                erection_IC,
                upvc_ic_jointing,
                provisions,
                binding.checkboxRemovalOfExcessSoil.isChecked,
                dust_filling


            )
            updateHSCActivity(updateActivity)


//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            /*val intent = Intent(this, ActivitiesActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)*/
        }
        binding.buttonAssignHscActivity.setOnClickListener {
            val hsc_activity_name = binding.editTextActivityName.text.toString().trim()
            val PCC_below_IC = binding.editTextPCCBelowIC.text.toString().trim()
            val erection_IC = binding.editTextErectionIC.text.toString().trim()
            val excavation_for_IC = binding.editTextIcExcavation.text.toString().trim()
            val upvc_ic_jointing = binding.editUpvcIcJointing.text.toString().trim()
            val provisions = binding.editProvisions.text.toString().trim()
            val dust_filling = binding.editTextDustFilling.text.toString().trim()
            val start_on = binding.editTextStartedOn.text.toString().trim()
            val hsc_status = binding.editTextCcStatus.text.toString().trim()
            val updateActivity = UpdateHscActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
                excavation_for_IC,
                PCC_below_IC,
                erection_IC,
                upvc_ic_jointing,
                provisions,
                binding.checkboxRemovalOfExcessSoil.isChecked,
                dust_filling


            )
            GlobalData.getInstance.updateHscActivity = updateActivity

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

    private fun updateHSCActivity(updateHscActivity: UpdateHscActivity) {
        viewModel.updateHSCActvity(updateHscActivity).observe(this, Observer {
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

/*//val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
//val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

lifecycleScope.launch {
    try {
        val taskResponse = viewModel.updateHSCActivity(hsc_activity_name,
            excavation_for_IC,PCC_below_IC,erection_IC,dust_filling,start_on,hsc_status,activity_id, task_id, plan_id, project_name, organization_name, username)
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


/*   //ENSURE the entity of hsc is correct. and change line 42.
       val hscAct by lazyDeferred {
           viewModel.activitiespipe(username, organization_name, project_name, plan_id, task_id, activity_name)
       }
       Coroutines.main {
           progress_bar.show()
           val orgs = hscAct.await()
           orgs.observe(this, Observer {
               progress_bar.hide()
               binding.editTextActivityName.setText(it.pipe_activity_name)
               binding.editTextStartedOn.setText(it.started_on)
               binding.editTextDustFilling.setText(it.bedding)
               binding.editTextErectionIC.setText(it.back_filling)
               binding.editTextIcExcavation.setText(it.laying)
               binding.editTextPCCBelowIC.setText(it.status)
               binding.editTextStartedOn.setText(it.trenching_pipeline)
               binding.editTextCcStatus.setText(it.status)
               // it.size.toString()
               // initRecyclerView(it.toOrganizationItem())
           })
       }*/
//