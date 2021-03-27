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
import com.example.hopelastrestart1.databinding.ActivityTaskhkBinding
import com.example.hopelastrestart1.databinding.ActivityTaskhscBinding
import com.example.hopelastrestart1.model.UpdateHouseKeepingActivity
import com.example.hopelastrestart1.model.UpdateTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.progress_bar
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception

//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class TaskhkActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskhkBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskhkBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(com.example.hopelastrestart1.viewmodel.TaskViewModel::class.java)


        //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        val task_name = intent.getStringExtra("task_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")

        binding.buttonUpdateHkActivity.setOnClickListener {
            val hk_activity_name = binding.editTextActivityName.text.toString().trim()
            val start_on = binding.editTextStartedOn.text.toString().trim()
            val hsc_status = binding.editTextCcStatus.text.toString().trim()
            val updateHkActivity = UpdateHouseKeepingActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id, activity_name, "True"
            )

            updateHKActivity(updateHkActivity)

//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.
            /* val intent = Intent(this, ActivitiesActivity::class.java)
             intent.putExtra("username", username)

             startActivity(intent)*/
        }
        binding.buttonAssignHkActivity.setOnClickListener {
            val hk_activity_name = binding.editTextActivityName.text.toString().trim()
            val start_on = binding.editTextStartedOn.text.toString().trim()
            val hsc_status = binding.editTextCcStatus.text.toString().trim()
            val updateHkActivity = UpdateHouseKeepingActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id, activity_name, "True"
            )
            GlobalData.getInstance.updateHouseKeepingActivity = updateHkActivity

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


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun updateHKActivity(updateTaskActivity: UpdateHouseKeepingActivity) {
        viewModel.updateHKActvity(updateTaskActivity).observe(this, Observer {
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
/*private fun updateActivity(
    username: String,
    organization_name: String,
    project_name: String,
    plan_id: String,
    task_id: String,
    activity_id: String
) {
    val hk_activity_name = binding.editTextActivityName.text.toString().trim()
    val start_on = binding.editTextStartedOn.text.toString().trim()
    val hsc_status = binding.editTextCcStatus.text.toString().trim()

    //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
    //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

    lifecycleScope.launch {
        try {
            val taskResponse = viewModel.updateHKActivity(
                hk_activity_name,
                start_on,
                hsc_status,
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
    }
}*/



