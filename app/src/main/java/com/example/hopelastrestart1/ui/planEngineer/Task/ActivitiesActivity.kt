package com.example.hopelastrestart1.ui.planEngineer.Task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener_CcAct
import com.example.hopelastrestart1.adapter.ccActivityAdapter
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ActivitiesActivity : AppCompatActivity(), KodeinAware, CellClickListener_CcAct {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)
        setSupportActionBar(toolbar1)
        var recycleview = findViewById<RecyclerView>(R.id.recyclerview_allActs)
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        recycleview.layoutManager = linearLayoutManager
        val username=intent.getStringExtra("username")

        val organization_name=intent.getStringExtra("organization_name")
        val project_name=intent.getStringExtra("project_name")
        val plan_id=intent.getStringExtra("plan_name")
        val task_id=intent.getStringExtra("task_id")
        val from_node=intent.getStringExtra("from_node")
        val to_node=intent.getStringExtra("to_node")
        val allActs by lazyDeferred {
            viewModel.activities1(username, organization_name, project_name, plan_id, task_id)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = allActs.await()
            orgs.observe(this, Observer {
                progress_bar.hide()
              //  adapter = CustomAdapter(it, this)
               // recycleview.setAdapter(adapter)
                recycleview.adapter = ccActivityAdapter(
                    it,
                    this,
                    this,
                    username,
                    organization_name,
                    project_name,
                    plan_id,
                    task_id, from_node, to_node
                )
                //        cc_task_id.setText(it.ccbreaking_activity_id)
                //        cc_startnode.setText(it.ccb_upvc_350)
                // initRecyclerView(it.toOrganizationItem())
            })
        }

    }

    override fun onCellClickListener(
        activity: Activit,
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        from_node: String,
        to_node:String,
        assign: Boolean
    ) {


        if(activity.activity_type=="CC Breaking")
        {
            if (assign==false) {
                val intent = Intent(this, TaskccActivity::class.java)
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
            }
            else
            {
                val intent = Intent(this, AssignTaskActivity::class.java)
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
            }
        }
        if(activity.activity_type=="Pipeline")
        {
            val intent =Intent(this, TaskpipeActivity::class.java)
            intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
            startActivity(intent)
        }
        if(activity.activity_type=="Manhole Erection")
        {
            val intent =Intent(this, TaskmhActivity::class.java)
            intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
            startActivity(intent)
        }
        if(activity.activity_type=="House Service Connection")
        {
            val intent =Intent(this, TaskhscActivity::class.java)
            intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
            startActivity(intent)
        }
        if(activity.activity_type=="House Keeping")
        {
            val intent =Intent(this, TaskhkActivity::class.java)
            intent.putExtra("activity_id", activity.activity_id)
            intent.putExtra("activity_name", activity.activity_name)
            intent.putExtra("activity_type", activity.activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_id)
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


}



