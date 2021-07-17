package com.example.hopelastrestart1.ui.siteEngineer

import RetrofitBuilder
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.fragmentAdapters.SiteEngFragmentAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivitySiteEngBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.model.GetUserProfile
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.example.hopelastrestart1.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_organization.*
import org.w3c.dom.Text

class SiteEngActivity : BaseActivity() {
    lateinit var tabLayout_site: TabLayout
    lateinit var viewPager_site: ViewPager
    lateinit var binding: ActivitySiteEngBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var projectViewModel: ProjectViewModel
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)

        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(MainViewModel::class.java)
        projectViewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
        title = "Tasks"
        tabLayout_site = findViewById<TabLayout>(R.id.tab_site)
        viewPager_site = findViewById(R.id.viewPager_site)
        tabLayout_site.addTab(tabLayout_site.newTab().setText("Received Tasks"))
        tabLayout_site.addTab(tabLayout_site.newTab().setText("Submitted Reports"))
        //  tabLayout_task.addTab(tabLayout_task.newTab().setText("AssignBy Task"))
        //tabLayout_task.addTab(tabLayout_task.newTab().setText("Dprs Received"))
        val getUserProfile = GetUserProfile(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!, ""
        )
       // getCurrentUserProfile(getUserProfile)

        setUpObserver(
            GetOrgModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!
            )
        )
        //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
        tabLayout_site.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout_site.setTabTextColors(
            getResources().getColor(R.color.clrLightBlue),
            getResources().getColor(R.color.clrMaroon)
        );
        val madapter = SiteEngFragmentAdapter.MyAdapter(
            this, supportFragmentManager,
            tabLayout_site.tabCount
        )
        viewPager_site!!.adapter = madapter
        viewPager_site.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabLayout_site
            )
        )
        tabLayout_site.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_site.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    private fun getCurrentUserProfile(getUserProfile: GetUserProfile) {
        viewModel.getCurrentUserProfile(getUserProfile).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { usersProfile -> usersProfile.body() }
                        val userProfile = resource.data?.body()
                        val header: View = navigationView.getHeaderView(0)
                        val headerTitle = header.findViewById<TextView>(R.id.tv_nav_header_title)
                        val navSubHeaderSubTitle =
                            header.findViewById<TextView>(R.id.tv_nav_header_subtitle)
                        headerTitle.setText(userProfile?.first_name)
                        navSubHeaderSubTitle.setText(userProfile?.user_email)


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

    private fun setUpObserver(getOrgModel: GetOrgModel) {

        mainViewModel.getOrgs(getOrgModel).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
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
        projectViewModel.getProjects(getPrjctModel).observe(this, Observer {
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