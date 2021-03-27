package com.example.hopelastrestart1.ui.home.project.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener1
import com.example.hopelastrestart1.adapter.OrganizationAdapter
import com.example.hopelastrestart1.adapter.ProjectRVAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.db.entities.Project
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.ui.home.plen.PlenActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.ui.home.project.AddProjectActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProjectsFragment : Fragment(), KodeinAware, CellClickListener1 {
    override val kodein by kodein()
    private val factory: ProjectViewModelFactory by instance()
    private lateinit var viewModel: ProjectViewModel
    private lateinit var recycleview: RecyclerView
    private lateinit var orgName: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_projects, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        //val username = requireActivity().intent.getStringExtra("username")
       // val orgId = requireActivity().intent.getIntExtra("organization_id", 0)
        orgName = requireActivity().intent.getStringExtra("organization_name")

        val getprjctModel =
            GetPrjctModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                orgName
            )

        setUpObserver(getprjctModel)
        /* val projects by lazyDeferred {
             viewModel.projects1(username, organization_name)
         }
         Coroutines.main {
             progress_bar.show()
             val orgs = projects.await()
             orgs.observe(viewLifecycleOwner, Observer {
                 progress_bar.hide()
                 it.size.toString()
                 recycleview.adapter = ProjectRVAdapter(it, this, username, organization_name)
             })
         }*/
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent = Intent(activity, AddProjectActivity::class.java)
            // intent.putExtra("username", username)
            intent.putExtra("organization_name", orgName)
             startActivity(intent)
        }

        return rootView
    }

    private fun initRecyclerView(projectItem: List<ProjectItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(projectItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view, ->
            //item.getItem(se)
            val intent = Intent(activity, PlenActivity::class.java)
        }
    }

    private fun List<Organization>.toOrganizationItem(): List<OrganizationItem> {
        return this.map {
            OrganizationItem(it)
        }
    }

    override fun onCellClickListener(
        project: com.example.hopelastrestart1.model.Project,
        username: String,
        organization_name: String
    ) {
        val intent = Intent(activity, PlenActivity::class.java)
        intent.putExtra("project_name", project.project_name)
        intent.putExtra("project_id", project.project_id)
        intent.putExtra("username", username)
        intent.putExtra("organization_name", organization_name)
        startActivity(intent)
    }

    private fun setUpObserver(getPrjctModel: GetPrjctModel) {
        viewModel.getProjects(getPrjctModel).observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects -> retrieveList(projects.body()?.projects!!) }
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

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
    }

    private fun retrieveList(projects: List<com.example.hopelastrestart1.model.Project>) {
        if (projects.size > 0) {
            GlobalData.getInstance.projects = projects
            recycleview.adapter =
                ProjectRVAdapter(projects, this, GlobalData.getInstance.userEmail!!, orgName)

            // recycleview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }
}




