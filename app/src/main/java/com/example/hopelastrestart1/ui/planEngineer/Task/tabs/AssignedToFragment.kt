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
import com.example.hopelastrestart1.data.db.entities.AssignedToMeEntity
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.ui.planEngineer.Task.*
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
class AssignToFragment : Fragment(), KodeinAware, AssignToRVAdapter.CellClickListener_AssignTotask {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_assigned_to, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_assign_to_task)
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")
        val plan_name = requireActivity().intent.getStringExtra("plan_name")
        val tasks by lazyDeferred {
            viewModel.assignTasks2(username, organization_name, project_name, plan_name )
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = tasks.await()
            orgs.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter = AssignToRVAdapter(it, this, username, organization_name, project_name, plan_name )
            })
        }
///
        return rootView
    }
    private fun initRecyclerView(assignTaskItem: List<AssignedTaskItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply{
            addAll(assignTaskItem)
        }
        recyclerview.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view,  ->
            //item.getItem(se)
            // how to fetch the onclick item value.
            //Ensure based upon the type of activity call the corresponding activity.
            val intent =  Intent(activity, TaskccActivity::class.java)
        }
    }
    private fun List<Organization>.toOrganizationItem() : List<OrganizationItem>{
        return this.map{
            OrganizationItem(it)
        }
    }


    override fun onCellClickListener(
        task: AssignedToMeEntity,
        username: String,
        organization_name: String,
        project_name: String,
        plan_name: String
    ) {
        val intent =  Intent(activity, ViewAssignedTaskActivity::class.java)
      //  intent.putExtra("task_name", task.activity_type_id)
//        intent.putExtra("task_id", task.activity_type_id)
        intent.putExtra("username", username)
        intent.putExtra("organization_name", organization_name)
        startActivity(intent)
    }
}



