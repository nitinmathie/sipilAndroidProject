package com.example.hopelastrestart1.ui.planEngineer.Task

import com.example.hopelastrestart1.R
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAddTaskBinding
import com.example.hopelastrestart1.model.AddTask
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class AddTaskActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Create Task"
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        //  viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(
            TaskViewModel
            ::class.java
        )        //ENSURE - the following are passed by previous screen.

        binding.buttonAddTask.setOnClickListener {
            val addTask = AddTask(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                GlobalData.getInstance.planName.toString(),
                binding.editTextTaskName.text.toString(),
                binding.editTextFromNode.text.toString(),
                binding.editTextToNode.text.toString(),
                binding.etRefStreetName.text.toString()
            )
            setUpObserver(addTask)
            /*val intent = Intent(this, PlenActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)*/
        }
    }

    private fun addTask(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String
    ) {
        val task_name = binding.editTextTaskName.text.toString().trim()
        val task_from_node = binding.editTextFromNode.text.toString().trim()
        val task_to_node = binding.editTextToNode.text.toString().trim()
        val task_description = binding.etRefStreetName.text.toString().trim()
        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        lifecycleScope.launch {


            /* try {
                 val taskResponse = viewModel.addTask(task_name, task_from_node , task_to_node, plan_id, project_name, organization_name, username)
                 if (taskResponse.isSuccessful != null) {
                     //call organization activity
                     val organizations by lazyDeferred {
                         viewModel.tasks1(username, organization_name, project_name, plan_id)
                     }
                 } else {
                     binding.rootLayout.snackbar(taskResponse.message!!)
                 }
             } catch (e: ApiException) {
                 e.printStackTrace()
             } catch (e: NoInternetException) {
                 e.printStackTrace()
             }*/
        }
    }

    private fun setUpObserver(addTask: AddTask) {
        viewModel.addTask(addTask).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { users ->
                            users.body()
                            if (users.body() != null) {
                                if (users.body()?.status_code.toString().equals("200") != null) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Created Successfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        it.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.hide()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }
                }

            }
        })
    }
}
