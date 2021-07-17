package com.example.hopelastrestart1.ui.admin

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.AdminFragmentAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAdminDashBoardBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.model.GetUserProfile
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.example.hopelastrestart1.viewmodel.UserViewModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_organization.*


class AdminDashBoardActivity : BaseActivity() {
    lateinit var binding: ActivityAdminDashBoardBinding
    private lateinit var viewModel: UserViewModel
    lateinit var mainViewModel: MainViewModel
    private lateinit var pviewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAdminDashBoardBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        //drawerLayout.addView(contentView, 0)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)

        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(MainViewModel::class.java)
        pviewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
        title = "Admin"
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("Reports")
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("DPRs")
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("DFRs")
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("P/L")
        )
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        binding.tabLayout.setTabTextColors(
            getResources().getColor(R.color.clrLightBlue),
            getResources().getColor(R.color.clrMaroon)
        );
        val madapter = AdminFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            binding.tabLayout.tabCount
        )
        binding.viewPager!!.adapter = madapter

        binding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tabLayout
            )
        )
        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        showItem()



        getOrgs(
            GetOrgModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!
            )
        )
    }

    private fun showItem() {
        val nav_Menu: Menu = navigationView.menu
        nav_Menu.findItem(R.id.nav_adduser).setVisible(true)
    }



    private fun getOrgs(getOrgModel: GetOrgModel) {
        mainViewModel.getOrgs(getOrgModel).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
//                        progress_bar.visibility = View.GONE
                        resource.data?.let { users ->
                            users.body()
                            GlobalData.getInstance.orgName =
                                users.body()?.organizations!![0].organization_name
                            val getprjctModel =
                                GetPrjctModel(
                                    GlobalData.getInstance.userEmail!!,
                                    GlobalData.getInstance.token!!,
                                    GlobalData.getInstance.orgName.toString()
                                )
                            getProjects(getprjctModel)

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

    private fun getProjects(getPrjctModel: GetPrjctModel) {
        pviewModel.getProjects(getPrjctModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects ->
                            if (projects.body() != null) {
                                if (projects.body()?.status_code.toString().equals("200")) {
                                    if (projects.body()?.projects!!.isNotEmpty()) {
                                        GlobalData.getInstance.projects = projects.body()?.projects
                                        GlobalData.getInstance.projectName = projects.body()?.projects!![0].project_name
                                    } else {
                                    }
                                } else if (projects.body()?.status_code.toString().equals("204")) {
                                }
                            } else {

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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        return super.onPrepareOptionsMenu(menu)
    }
}