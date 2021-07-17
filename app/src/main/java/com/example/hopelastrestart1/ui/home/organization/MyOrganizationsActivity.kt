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
import com.example.hopelastrestart1.adapter.MyOrganizationsAdapter
import com.example.hopelastrestart1.adapter.MyOrgsItemCellListener
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.databinding.ActivityMyOrganizationsBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetOrgProjectRoles
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.project.MyProjectsActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*

class MyOrganizationsActivity : BaseActivity(), MyOrgsItemCellListener {
    lateinit var binding: ActivityMyOrganizationsBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityMyOrganizationsBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerview.layoutManager = linearLayoutManager
        title = "My Organizations"
        setupViewModel()

        val add = findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent = Intent(this, AddOrganizationActivity::class.java)
// intent.putExtra("username", userName)
            startActivity(intent)
        }
    }


    override fun onCellClickListener(organization: Organization) {
        val intent = Intent(this, MyProjectsActivity::class.java)
        GlobalData.getInstance.orgName = organization.organization_name
        binding.tvCureentOrg.setText(organization.organization_name)
        startActivity(intent)
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
                            if (users.body() != null) {
                                if (users.body()?.status_code.equals("200")) {
                                    if (users.body()?.organizations!!.isNotEmpty()) {
                                        GlobalData.getInstance.orgName =
                                            users.body()?.organizations!![0].organization_name
                                        binding.tvCureentOrg.setText(
                                            users.body()?.organizations!![0].organization_name
                                        )
                                        retrieveList(users.body()?.organizations!!)
                                    } else {
                                        binding.tvCreateOrg.visibility = View.VISIBLE
                                    }


                                } else if (users.body()?.status_code.equals("204")) {
                                    binding.tvCreateOrg.visibility = View.VISIBLE

                                }
                            } else {
                                binding.tvCreateOrg.visibility = View.VISIBLE
                            }
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

    private fun retrieveList(users: List<Organization>) {
        if (users.size > 0) {
            binding.recyclerview.adapter = MyOrganizationsAdapter(users, this)
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }

    private fun getRoles(getOrgProjectRoles: GetOrgProjectRoles, orgName: String) {
        viewModel.getRoles(getOrgProjectRoles).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let {
                            if (it.body()?.organizations_projects_roles!!.isNotEmpty()) {
                                val roles = it.body()?.organizations_projects_roles!!
                                for (i in it.body()?.organizations_projects_roles!!.indices) {
                                    if (roles[i].organization_name.equals(orgName)) {
                                        if (roles[i].organization_name.equals("0")) {
                                            val intent =
                                                Intent(this, OrganizationActivity::class.java)
                                            startActivity(intent)
                                        } else if (roles[i].organization_name.equals("1")) {
                                            val intent =
                                                Intent(this, PlanActivity::class.java)
                                            startActivity(intent)
                                        } else if (roles[i].organization_name.equals("2")) {
                                            val intent =
                                                Intent(this, SiteEngActivity::class.java)
                                            startActivity(intent)
                                        }
                                    }
                                }

                            } else {

                            }
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

    override fun onResume() {
        super.onResume()
        val getOrgModel =
            GetOrgModel(GlobalData.getInstance.userEmail!!, GlobalData.getInstance.token!!)
        setUpObserver(getOrgModel)
    }

}

