package com.example.hopelastrestart1.ui.planEngineer.Task
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.ui.home.plen.PlenActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.databinding.ActivityAddTaskBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.snackbar
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class AddTaskActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        //ENSURE - the following are passed by previous screen.
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_id")
        binding.buttonAddTask.setOnClickListener {
            addTask(username, organization_name , project_name, plan_id)
            val intent = Intent(this, PlenActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }
    }
    private fun addTask(username:String, organization_name: String, project_name:String, plan_id: String ){
        val task_name = binding.editTextTaskName.text.toString().trim()
        val task_from_node = binding.editTextFromNode.text.toString().trim()
        val task_to_node = binding.editTextToNode.text.toString().trim()
        val task_description = binding.editTextTaskDescription.text.toString().trim()
        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        lifecycleScope.launch {
            try {
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
            }
        }
    }
}
