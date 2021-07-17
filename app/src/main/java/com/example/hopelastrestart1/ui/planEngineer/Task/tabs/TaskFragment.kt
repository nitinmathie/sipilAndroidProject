package com.example.hopelastrestart1.ui.planEngineer.Task.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.data.network.responses.GetTaskResponse
import com.example.hopelastrestart1.data.network.responses.PlanResponse
import com.example.hopelastrestart1.model.AddPlan
import com.example.hopelastrestart1.model.GetTasks
import com.example.hopelastrestart1.ui.planEngineer.Task.ActivitiesActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AddTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class TaskFragment : Fragment(), KodeinAware, CellClickListener_task {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    lateinit var response: GetTaskResponse
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var plan_name: String
    lateinit var username: String
    lateinit var organization_name: String
    lateinit var project_name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_task)
        tvCreate = rootView.findViewById<TextView>(R.id.tv_create)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        /*username = requireActivity().intent.getStringExtra("username")
        organization_name = requireActivity().intent.getStringExtra("organization_name")
        project_name = requireActivity().intent.getStringExtra("project_name")
        plan_name = requireActivity().intent.getStringExtra("plan_name")*/





        // val plan_name = requireActivity().intent.getStringExtra("plan_name")
        /*val tasks by lazyDeferred {
            viewModel.tasks1(username, organization_name, project_name, plan_name )
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = tasks.await()
            orgs.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter = taskRVAdapter(it, this, username, organization_name, project_name, plan_name )
            })
        }*/
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_task)
        add.setOnClickListener {
            val intent = Intent(activity, AddTaskActivity::class.java)
            startActivity(intent)
        }
        /* val add1 = rootView.findViewById<Button>(R.id.btn_assign_daily_task)
         add.setOnClickListener {
             val intent = Intent(activity, AssignTaskActivity::class.java)
             intent.putExtra("username", username)
             intent.putExtra("organization_name", organization_name)
             intent.putExtra("project_name", project_name)
             intent.putExtra("plan_name", plan_name)
             startActivity(intent)
         }*/
        return rootView
    }

    private fun initRecyclerView(taskItem: List<TaskItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(taskItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view, ->
            //item.getItem(se)
            val intent = Intent(activity, ActivitiesActivity::class.java)
        }
    }

    private fun List<Organization>.toOrganizationItem(): List<OrganizationItem> {
        return this.map {
            OrganizationItem(it)
        }
    }

    override fun onCellClickListener(
        task: Task
    ) {
        val intent = Intent(activity, ActivitiesActivity::class.java)
        GlobalData.getInstance.task = task
        /* val plan_name = plan_name.toString()
         val taskid = (task.task_id).toString()
         intent.putExtra("task_name", task.task_name)
         intent.putExtra("from_node", task.task_startnode)
         intent.putExtra("to_node", task.task_endnode)
         intent.putExtra("task_id", taskid)
         intent.putExtra("username", username)
         intent.putExtra("organization_name", organization_name)
         intent.putExtra("project_name", project_name)
         intent.putExtra("plan_name", plan_name)*/
        startActivity(intent)
    }

    private fun setUpObserver(getTasks: GetTasks) {
        viewModel.getTasks(getTasks).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { taskResponse ->
                            response = taskResponse.body()!!
                            if (response.taskinfo != null) {
                                if (response.taskinfo!!.size != 0) {
                                    recycleview.adapter = taskRVAdapter(
                                        response.taskinfo!!,
                                        this,
                                    )

                                } else {
                                    tvCreate.visibility = View.VISIBLE

                                }
                            } else {
                                tvCreate.visibility = View.VISIBLE

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

    override fun onResume() {
        super.onResume()
        val getTasks = GetTasks(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            GlobalData.getInstance.planName.toString(),

            )
        setUpObserver(getTasks)
    }
}




