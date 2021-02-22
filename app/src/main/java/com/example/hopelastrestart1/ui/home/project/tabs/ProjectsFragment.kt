package com.example.hopelastrestart1.ui.home.project.tabs

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
import com.example.hopelastrestart1.adapter.CellClickListener1
import com.example.hopelastrestart1.adapter.ProjectRVAdapter
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.db.entities.Project
import com.example.hopelastrestart1.ui.home.plen.PlenActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.ui.home.project.AddProjectActivity
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_projects, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        viewModel = ViewModelProviders.of(this, factory).get(ProjectViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val projects by lazyDeferred {
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
        }
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent =Intent(activity, AddProjectActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            startActivity(intent)
        }

        return rootView
    }
    private fun initRecyclerView(projectItem: List<ProjectItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply{
            addAll(projectItem)
        }
        recyclerview.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view,  ->
            //item.getItem(se)
            val intent =  Intent(activity, PlenActivity::class.java)
        }
    }
    private fun List<Organization>.toOrganizationItem() : List<OrganizationItem>{
        return this.map{
            OrganizationItem(it)
        }
    }

    override fun onCellClickListener(project: Project, username: String, organization_name: String) {
        val intent =  Intent(activity, PlenActivity::class.java)
        intent.putExtra("project_name", project.project_name)
        intent.putExtra("project_id", project.project_id)

        intent.putExtra("username", username)
        intent.putExtra("organization_name", organization_name)
        startActivity(intent)
    }


}




