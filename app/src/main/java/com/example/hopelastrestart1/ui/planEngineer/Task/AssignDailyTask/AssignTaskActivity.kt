package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAssignTaskBinding
import com.example.hopelastrestart1.databinding.ActivityAssignTaskMachineryBinding
import com.example.hopelastrestart1.model.GetMachinesAndMaterialModel
import com.example.hopelastrestart1.model.GetRoledBasedUsers
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_assign_task.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AssignTaskActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    private lateinit var userSpinner: Spinner
    private lateinit var binding: ActivityAssignTaskBinding
    public lateinit var t: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAssignTaskBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Assign Task"
        userSpinner = findViewById<Spinner>(R.id.spinner_user)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val activity_name = intent.getStringExtra("activity_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        var letters: MutableList<Char> = mutableListOf<Char>()
        val myAdapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)

        // make an array of users with role type = Site Engineer

        binding.activityType.setText(activity_name.toString())
        binding.editTextFromNode.setText(GlobalData.getInstance.task?.task_startnode.toString())
        binding.editTextToNode.setText(GlobalData.getInstance.task?.task_endnode.toString())
        spinner_user.adapter = myAdapter

        val getRoledBasedUsers = GetRoledBasedUsers(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            organization_name, project_name, plan_name, "2"
        )
        getRoleBasedUsers(getRoledBasedUsers)
        /* spinner_user?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
             override fun onNothingSelected(parent: AdapterView<*>?) {
             }

             override fun onItemSelected(
                 parent: AdapterView<*>?,
                 view: View?,
                 position: Int,
                 id: Long
             ) {
                 val item = parent!!.getItemAtPosition(position).toString()
                 binding.err.setText(item)
             }
         }*/
        //on button click
        val add = findViewById<Button>(R.id.btn_submit_activity_machinery)
        add.setOnClickListener {

            GlobalData.getInstance.estimatedTimeLine = binding.etEstimatedTimeline.text.toString()
            GlobalData.getInstance.assignedTo = binding.spinnerUser.selectedItem.toString()
//            val str: String = spinner_user.getSelectedItem().toString()
            val intent = Intent(this, AssignTaskMachinery::class.java)
            intent.putExtra("activity_name", activity_name)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("plan_name", plan_name)
            intent.putExtra("project_name", project_name)
            startActivity(intent)
        }


    }

    fun append(users: Array<String>, username: String?): Array<String> {
        val list: MutableList<String> = users.toMutableList()
        if (username != null) {
            list.add(username)
        }
        return list.toTypedArray()

    }

    private fun getRoleBasedUsers(getRoleBasedUsers: GetRoledBasedUsers) {
        viewModel.getRoleBasedUsers(getRoleBasedUsers).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { usersResponse ->
                            usersResponse.body()
                            var usersArray = arrayOf<String>()
                            val users = usersResponse.body()?.users
                            for (element in users!!) {
                                usersArray = append(
                                    usersArray,
                                    element.user_email
                                )
                                /*  element.user_firstname + "(" + element.user_email + ")"*/
                            }
                            val projects_adapter =
                                ArrayAdapter(
                                    this,
                                    android.R.layout.simple_expandable_list_item_1,
                                    usersArray
                                )
                            binding.spinnerUser.adapter = projects_adapter

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


//
