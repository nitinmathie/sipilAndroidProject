package com.example.hopelastrestart1.ui.home.organization

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
import com.example.hopelastrestart1.adapter.CellClickListener
import com.example.hopelastrestart1.adapter.OrganizationAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.databinding.ActivityOrganizationBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetOrgProjectRoles
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.activity_organization.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class OrganizationActivity : BaseActivity(), KodeinAware, CellClickListener {
    override val kodein by kodein()
    private val factory: OrganizationViewModelFactory by instance()
    private lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityOrganizationBinding

    // private lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityOrganizationBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        var token = GlobalData.getInstance.token
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerview.layoutManager = linearLayoutManager
        setupViewModel()


        val getOrgProjectRoles = GetOrgProjectRoles(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!
        )
        //   getRoles(getOrgProjectRoles)
        val add = findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent = Intent(this, AddOrganizationActivity::class.java)
// intent.putExtra("username", userName)
            startActivity(intent)
        }

    }

    private fun initRecyclerView(organizationItem: List<OrganizationItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(organizationItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view ->
//item.getItem(se)
            val intent = Intent(this, ProjectActivity::class.java)
        }
    }

    private fun List<Organization>.toOrganizationItem(): List<OrganizationItem> {
        return this.map {
            OrganizationItem(it)
        }
    }

    override fun onCellClickListener(organization: Organization, username: String) {
        val intent = Intent(this, ProjectActivity::class.java)
        GlobalData.getInstance.orgName = organization.organization_name
        startActivity(intent)
      /*  getRoles(
            GetOrgProjectRoles(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!
            ), organization.organization_name.toString()
        )*/
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(MainViewModel::class.java)
    }

    private fun setUpObserver(getOrgModel: GetOrgModel) {
        viewModel.getOrgs(getOrgModel).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { users ->
                            users.body()?.organizations
                            if (users.body() != null) {
                                if (users.body()?.organizations != null) {
                                    retrieveList(users.body()?.organizations!!)
                                    val getOrgProjectRoles = GetOrgProjectRoles(
                                        GlobalData.getInstance.userEmail!!,
                                        GlobalData.getInstance.token!!
                                    )
                                    //   getRoles(getOrgProjectRoles)

                                } else {
                                    binding.tvCreateOrg.visibility = View.VISIBLE
                                }
                            } else {
                                binding.tvCreateOrg.visibility = View.VISIBLE
                            }

                        }
                    }
                    Status.ERROR -> {
                        /*D/OkHttp: {"error":"organization doesnot exist","status_code":204}*/
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })
    }


    private fun retrieveList(users: List<Organization>) {
        if (users.size > 0) {
            binding.recyclerview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }


    override fun onResume() {
        super.onResume()
        val getOrgModel =
            GetOrgModel(GlobalData.getInstance.userEmail!!, GlobalData.getInstance.token!!)
        setUpObserver(getOrgModel)
    }





}