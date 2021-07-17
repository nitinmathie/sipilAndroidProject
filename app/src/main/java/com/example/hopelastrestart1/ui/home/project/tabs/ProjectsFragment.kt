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
import com.example.hopelastrestart1.adapter.ProjectRVAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.ui.home.project.AddProjectActivity
import com.example.hopelastrestart1.util.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.fragment_projects.view.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProjectsFragment : Fragment(), KodeinAware, CellClickListener1 {
    override val kodein by kodein()
    private val factory: ProjectViewModelFactory by instance()
    private lateinit var viewModel: ProjectViewModel
    private lateinit var rootView: View
    private lateinit var recycleview: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_projects, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        //val username = requireActivity().intent.getStringExtra("username")
        // val orgId = requireActivity().intent.getIntExtra("organization_id", 0)


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
            val intent = Intent(activity, PlanActivity::class.java)
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
        val intent = Intent(activity, PlanActivity::class.java)
        GlobalData.getInstance.navgationType = "projects"
        GlobalData.getInstance.projectName = project.project_name
        startActivity(intent)
    }

    private fun getProjects(getPrjctModel: GetPrjctModel) {
        viewModel.getProjects(getPrjctModel).observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rootView.progress_bar.hide()
                        resource.data?.let {
                            if (it.body()?.status_code.toString().equals("200")) {
                                rootView.tv_create_org.visibility = View.GONE
                                retrieveList(it.body()?.projects!!)
                            } else {
                                rootView.tv_create_org.visibility = View.VISIBLE
                            }
                        }
                    }
                    Status.ERROR -> {
                        rootView.progress_bar.hide()
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        rootView.progress_bar.show()
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
                ProjectRVAdapter(
                    projects,
                    this,
                    GlobalData.getInstance.userEmail!!,
                    GlobalData.getInstance.orgName.toString()
                )

            // recycleview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        val getprjctModel =
            GetPrjctModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString()
            )
        getProjects(getprjctModel)
    }
}




