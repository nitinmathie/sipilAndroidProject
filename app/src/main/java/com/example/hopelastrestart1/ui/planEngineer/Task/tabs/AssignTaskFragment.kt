package com.example.hopelastrestart1.ui.planEngineer.Task.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.*
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.*
import com.example.hopelastrestart1.data.network.responses.GetAssignedTasksResposne
import com.example.hopelastrestart1.data.network.responses.GetTaskResponse
import com.example.hopelastrestart1.model.GetAssignedTaskActivitesModel
import com.example.hopelastrestart1.model.GetTasksAssignedToMe
import com.example.hopelastrestart1.ui.planEngineer.Task.*
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class AssignTaskFragment : Fragment(), KodeinAware, CellClickListener_Assigntask {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: GetAssignedTasksResposne
    lateinit var plan_name: String
    lateinit var username: String
    lateinit var organization_name: String
    lateinit var project_name: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_assign_task, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_assign_task)
        tvCreate = rootView.findViewById<TextView>(R.id.tv_create)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        username = requireActivity().intent.getStringExtra("username")
        organization_name = requireActivity().intent.getStringExtra("organization_name")
        project_name = requireActivity().intent.getStringExtra("project_name")
        plan_name = requireActivity().intent.getStringExtra("plan_name")


        assignedTasks(
            GetTasksAssignedToMe(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!
            )
        )
        // recycleview.adapter = AssignRVAdapter(it, this, username, organization_name, project_name, plan_name )

        /* val tasks by lazyDeferred {
             viewModel.assignTasks1(username, organization_name, project_name, plan_name )
         }
         Coroutines.main {
             progress_bar.show()
             val orgs = tasks.await()
             orgs.observe(viewLifecycleOwner, Observer {
                 progress_bar.hide()
                 it.size.toString()
                 recycleview.adapter = AssignRVAdapter(it, this, username, organization_name, project_name, plan_name )
             })
         }*/
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_assign_task)
        add.setOnClickListener {

            val intent = Intent(activity, AssignTaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_name)
            startActivity(intent)
        }
        return rootView
    }

    /* override fun onCellClickListener(
         task: AssignedByActivity,
         username: String,
         organization_name: String,
         project_name: String,
         plan_name: String
     ) {
         val intent = Intent(activity, ViewAssignedTaskActivity::class.java)
         //  intent.putExtra("task_name", task.activity_type_id)
         // intent.putExtra("task_id", task.activity_type_id)
         //intent.putExtra("username", username)
         //intent.putExtra("organization_name", organization_name)
         startActivity(intent)
     }*/

    private fun assignedTasks(getTasks: GetTasksAssignedToMe) {
        viewModel.getAssignedTasks(getTasks).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { resp ->
                            response = resp.body()!!
                            if (response.assigned_activities.size != 0) {
                                recycleview.adapter = AssignRVAdapter(
                                    response.assigned_activities!!,
                                    this,
                                    username,
                                    organization_name,
                                    project_name,
                                    plan_name
                                )
                            }

                        }

                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })
    }

    override fun onCellClickListener(
        task: GetAssignedTaskActivitesModel,
        username: String,
        organization_name: String,
        project_name: String,
        plan_name: String
    ) {
        TODO("Not yet implemented")
    }
}




