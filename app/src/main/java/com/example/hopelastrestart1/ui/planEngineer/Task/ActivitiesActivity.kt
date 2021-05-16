package com.example.hopelastrestart1.ui.planEngineer.Task

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener_CcAct
import com.example.hopelastrestart1.adapter.ccActivityAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.databinding.ActivityActivitiesBinding
import com.example.hopelastrestart1.model.GetTaskActivities
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ActivitiesActivity : BaseActivity(), KodeinAware, CellClickListener_CcAct {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    lateinit var binding: ActivityActivitiesBinding
    lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_activities)
        //  setSupportActionBar(toolbar1)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Activities"
        //   var recycleview = findViewById<RecyclerView>(R.id.recyclerview_allActs)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )

        task = GlobalData.getInstance.task!!

        binding.recyclerviewAllActs.layoutManager = linearLayoutManager
        /* val username = intent.getStringExtra("username")
         val organization_name = intent.getStringExtra("organization_name")
         val project_name = intent.getStringExtra("project_name")
         val plan_name = intent.getStringExtra("plan_name")
         task_name = intent.getStringExtra("task_name")
         val from_node = GlobalData.getInstance.task?.task_startnode
         val to_node = GlobalData.getInstance.task?.task_endnode*/
        val allActs: List<Activit>
        allActs = emptyList();


        val getTaskActivities = GetTaskActivities(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            GlobalData.getInstance.planName.toString(), task.task_name.toString()
        )
        getActivities(getTaskActivities)
    }

    override fun onCellClickListener(
        activity: Activit,
        assign: Boolean
    ) {
        GlobalData.getInstance.activity = activity
        if (activity.activity_type == "CC Breaking") {
            // if (assign == false) {
            val intent = Intent(this, TaskccActivity::class.java)
            startActivity(intent)

            /* } else {
                 val intent = Intent(this, AssignTaskActivity::class.java)
                 GlobalData.getInstance.activity = activity
                 intent.putExtra("activity_id", activity.activity_id)
                 intent.putExtra("activity_name", activity.activity_name)
                 intent.putExtra("activity_type", activity.activity_type)
                 intent.putExtra("task_id", task_id)
                 intent.putExtra("from_node", from_node)
                 intent.putExtra("to_node", to_node)
                 intent.putExtra("username", username)
                 intent.putExtra("organization_name", organization_name)
                 intent.putExtra("project_name", project_name)
                 intent.putExtra("plan_name", plan_id)
                 startActivity(intent)
             }*/
        } else if (activity.activity_type == "Pipeline") {
            val intent = Intent(this, TaskpipeActivity::class.java)
            /*   intent.putExtra("activity_id", activity.activity_id)
               intent.putExtra("activity_name", activity.activity_name)
               intent.putExtra("activity_type", activity.activity_type)
               intent.putExtra("task_id", task_id)
               intent.putExtra("task_name", task.task_name)
               GlobalData.getInstance.activity = activity
               intent.putExtra("username", username)
               intent.putExtra("organization_name", organization_name)
               intent.putExtra("project_name", project_name)
               intent.putExtra("plan_name", plan_id)*/
            startActivity(intent)
        } else if (activity.activity_type == "Manhole Erection") {
            val intent = Intent(this, TaskmhActivity::class.java)
            /*intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("task_name", task.task_name)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)*/
            startActivity(intent)
        } else if (activity.activity_type == "House Service Connection") {
            val intent = Intent(this, TaskhscActivity::class.java)
            /* intent.putExtra("activity_id", activity.activity_id)
             intent.putExtra("activity_name", activity.activity_name)
             intent.putExtra("task_name", task.task_name)
             intent.putExtra("activity_type", activity.activity_type)
             intent.putExtra("task_id", task_id)
             intent.putExtra("username", username)
             intent.putExtra("organization_name", organization_name)
             intent.putExtra("project_name", project_name)
             intent.putExtra("plan_name", plan_id)*/
            startActivity(intent)
        } else if (activity.activity_type == "House Keeping") {
            val intent = Intent(this, TaskhkActivity::class.java)
            /* intent.putExtra("activity_id", activity.activity_id)
             intent.putExtra("activity_name", activity.activity_name)
             intent.putExtra("activity_type", activity.activity_type)
             intent.putExtra("task_id", task_id)
             intent.putExtra("username", username)
             intent.putExtra("task_name", task.task_name)
             intent.putExtra("organization_name", organization_name)
             intent.putExtra("project_name", project_name)
             intent.putExtra("plan_name", plan_id)*/
            startActivity(intent)
        } else if (activity.activity_type == "Road Restoration") {
            val intent = Intent(this, TaskRoadRestorationActivity::class.java)
            /*intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("username", username)
            intent.putExtra("task_name", task.task_name)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)*/
            startActivity(intent)
        }

        // val plan_name = plan_name.toString()
        //val task_name = (task.task_id).toString()
        //    intent.putExtra("task_name", task.task_name)
        //    intent.putExtra("task_id", task_name)
        //    intent.putExtra("username", username)
        //    intent.putExtra("organization_name", organization_name)
        //    intent.putExtra("project_name", project_name)
        //    intent.putExtra("plan_name", plan_name)
        //   startActivity(intent)
    }


    private fun getActivities(
        getTaskActivities: GetTaskActivities
    ) {
        viewModel.getTaskActivites(getTaskActivities).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { activities ->
                            activities.body()
                            binding.recyclerviewAllActs.adapter = ccActivityAdapter(
                                activities.body()!!.task_activities,
                                this,
                                this,

                                )
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




