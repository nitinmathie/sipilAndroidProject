package com.example.hopelastrestart1.ui.home.project

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
import com.example.hopelastrestart1.adapter.MyProjectsItemCellListener
import com.example.hopelastrestart1.adapter.MyprojectsRVAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityMyProjectsBinding
import com.example.hopelastrestart1.model.GetOrgProjectRoles
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.model.Project
import com.example.hopelastrestart1.ui.admin.AdminDashBoardActivity
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class MyProjectsActivity : BaseActivity(), MyProjectsItemCellListener {

    lateinit var binding: ActivityMyProjectsBinding
    private lateinit var viewModel: ProjectViewModel
    lateinit var mainViewModel: MainViewModel

    private lateinit var recycleview: RecyclerView
    private lateinit var orgName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityMyProjectsBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        //drawerLayout.addView(contentView, 0)
        title = "My Projects"
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)

        mainViewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(MainViewModel::class.java)
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.recyclerview.layoutManager = linearLayoutManager

        binding.tvCureentOrg.setText(GlobalData.getInstance.orgName)
        binding.fab.setOnClickListener {
            val intent = Intent(applicationContext, AddProjectActivity::class.java)
            // intent.putExtra("username", username)
            startActivity(intent)
        }

    }

    private fun getProjects(getPrjctModel: GetPrjctModel) {
        viewModel.getProjects(getPrjctModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects ->
                            if (projects.body() != null) {
                                if (projects.body()?.status_code.toString().equals("200")) {
                                    if (projects.body()?.projects!!.isNotEmpty()) {
                                        retrieveList(projects.body()?.projects!!)
                                        GlobalData.getInstance.projects = projects.body()?.projects
                                        GlobalData.getInstance.projectName = projects.body()?.projects!![0].project_name
                                    } else {
                                        binding.tvCreateOrg.visibility = View.VISIBLE
                                    }
                                } else if (projects.body()?.status_code.toString().equals("204")) {
                                    binding.tvCreateOrg.visibility = View.VISIBLE
                                }
                            } else {
                                binding.tvCreateOrg.visibility = View.VISIBLE

                            }

                        }

                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })


    }

    private fun retrieveList(projects: List<com.example.hopelastrestart1.model.Project>) {
        if (projects.size > 0) {
            GlobalData.getInstance.projects = projects
            binding.recyclerview.adapter = MyprojectsRVAdapter(projects, this)

            // recycleview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }

    override fun onCellClickListener(
        project: Project
    ) {
        if(GlobalData.getInstance.loginRole.equals("0")){
            startActivity(Intent(applicationContext, StoreActivity::class.java))
        }



        /* if (GlobalData.getInstance.loginRole.equals("1")) {
             GlobalData.getInstance.navgationType = "myprojects"
             GlobalData.getInstance.projectName = project.project_name
             val intent = Intent(applicationContext, PlenActivity::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
             startActivity(intent)
             finish()
         } else if (GlobalData.getInstance.loginRole.equals("2")) {
             GlobalData.getInstance.navgationType = "myprojects"
             GlobalData.getInstance.projectName = project.project_name
             val intent = Intent(applicationContext, SiteEngActivity::class.java)
             startActivity(intent)
             //finish()
         } else if (GlobalData.getInstance.loginRole.equals("Admin")) {
             GlobalData.getInstance.projectName = project.project_name
             val intent = Intent(applicationContext, AdminDashBoardActivity::class.java)
             startActivity(intent)
         }*/
      /*  getRoles(
            GetOrgProjectRoles(
                GlobalData.getInstance.userEmail.toString(),
                GlobalData.getInstance.token.toString()
            )
            , project.project_name.toString()
        )*/


    }

    private fun getRoles(getOrgProjectRoles: GetOrgProjectRoles, projectName: String) {
        mainViewModel.getRoles(getOrgProjectRoles).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let {
                            if (it.body()?.organizations_projects_roles!!.isNotEmpty()) {
                                val roles = it.body()?.organizations_projects_roles!!
                                for (i in it.body()?.organizations_projects_roles!!.indices) {
                                    if (roles[i].project_name.equals(projectName)) {
                                        if (roles[i].role.equals("0")) {
                                            val intent =
                                                Intent(this, AdminDashBoardActivity::class.java)
                                            startActivity(intent)
                                        } else if (roles[i].role.equals("1")) {
                                            GlobalData.getInstance.navgationType = "myprojects"
                                            GlobalData.getInstance.projectName = projectName
                                            val intent =
                                                Intent(applicationContext, PlanActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else if (roles[i].role.equals("2")) {
                                            GlobalData.getInstance.navgationType = "myprojects"
                                            GlobalData.getInstance.projectName = projectName
                                            val intent = Intent(
                                                applicationContext,
                                                SiteEngActivity::class.java
                                            )
                                            startActivity(intent)
                                            // finish()
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
        val getprjctModel =
            GetPrjctModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString()
            )

        getProjects(getprjctModel)
    }
}