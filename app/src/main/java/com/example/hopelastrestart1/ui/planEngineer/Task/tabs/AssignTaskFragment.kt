package com.example.hopelastrestart1.ui.planEngineer.Task.tabs
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.*
import com.example.hopelastrestart1.data.db.entities.*
import com.example.hopelastrestart1.ui.planEngineer.Task.*
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
class AssignTaskFragment : Fragment(), KodeinAware, CellClickListener_Assigntask {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_assign_task, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_assign_task)
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")
        val plan_name = requireActivity().intent.getStringExtra("plan_name")
        val tasks by lazyDeferred {
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
        }
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_assign_task)
        add.setOnClickListener {

            val intent =Intent(activity, AssignTaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_name)
            startActivity(intent)
        }
        return rootView
    }
    override fun onCellClickListener(
        task: AssignedByActivity,
        username: String,
        organization_name: String,
        project_name: String,
        plan_name: String
    ) {
        val intent =  Intent(activity, ViewAssignedTaskActivity::class.java)
      //  intent.putExtra("task_name", task.activity_type_id)
       // intent.putExtra("task_id", task.activity_type_id)
        //intent.putExtra("username", username)
        //intent.putExtra("organization_name", organization_name)
        startActivity(intent)
    }
}




