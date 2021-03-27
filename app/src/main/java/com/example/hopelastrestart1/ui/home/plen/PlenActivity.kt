package com.example.hopelastrestart1.ui.home.plen

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.SplashScreenActivity
import com.example.hopelastrestart1.adapter.CellClickListenerPlan
import com.example.hopelastrestart1.adapter.PlanAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.data.network.responses.PlanResponse
import com.example.hopelastrestart1.databinding.ActivityPlenBinding
import com.example.hopelastrestart1.model.GetPlans
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.PlanViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class PlenActivity : BaseActivity(), KodeinAware, CellClickListenerPlan {
    override val kodein by kodein()

    lateinit var tabLayout_plen: TabLayout
    lateinit var viewPager_plen: ViewPager
    lateinit var binding: ActivityPlenBinding
    lateinit var viewModel: PlanViewModel
    lateinit var orgName: String
    lateinit var projectName: String
    var context: Context? = null
    lateinit var response: PlanResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        //setContentView(R.layout.activity_plen)
        title = "Plans"
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityPlenBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.recyclerviewPlan.layoutManager = linearLayoutManager

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(
            PlanViewModel
            ::class.java
        )

        orgName = intent.getStringExtra("organization_name")
        projectName = intent.getStringExtra("project_name")
        val getPlans = GetPlans(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            orgName, projectName
        )
        setUpObserver(getPlans, applicationContext)
        //   setUpObserver()

        binding.fabPlan.setOnClickListener {
            val intent = Intent(this, AddPlenActivity::class.java)
            intent.putExtra("organization_name", orgName)
            intent.putExtra("project_name", projectName)
            startActivity(intent)
            //  finish()
        }


        /* tabLayout_plen = findViewById<TabLayout>(R.id.TabLayout_Plen)
         viewPager_plen = findViewById(R.id.viewPager_Plen)
         tabLayout_plen.addTab(tabLayout_plen.newTab().setText("Plans"))
        // tabLayout_plen.addTab(tabLayout_plen.newTab().setText("test"))
         //tabLayout.addTab(tabLayout.newTab().setText("Stores"))
         tabLayout_plen.tabGravity = TabLayout.GRAVITY_FILL
         val madapter = PlanFragmentAdapter.MyAdapter(
             this, supportFragmentManager,
             tabLayout_plen.tabCount
         )
         viewPager_plen!!.adapter = madapter
         viewPager_plen.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_plen))
         tabLayout_plen.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
             override fun onTabSelected(tab: TabLayout.Tab) {
                 viewPager_plen.currentItem = tab.position
             }
             override fun onTabUnselected(tab: TabLayout.Tab) {}
             override fun onTabReselected(tab: TabLayout.Tab) {}
         })*/
    }

    private fun setUpObserver(
        getPlans: GetPlans,
        applicationContext: Context
    ) {
        viewModel.getPlans(getPlans).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { plans ->
                            response = plans.body()!!

                        }
                        if (response.plans != null) {
                            if (response?.plans!!.size != 0) {
                                binding.recyclerviewPlan.adapter = PlanAdapter(
                                    applicationContext,
                                    response?.plans!!,
                                    PlenActivity(),
                                    "username",
                                    orgName,
                                    projectName
                                )

                            } else {
                                binding.tvCreatePlan.visibility = View.VISIBLE
                            }
                        } else {
                            binding.tvCreatePlan.visibility = View.VISIBLE
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

    /* override fun onCellClickListener(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    ) {

        val intent =  Intent(this, TaskActivity::class.java)
        //intent.putExtra("plan_name", plan.plan_id)
        // intent.putExtra("organization_name", organization_name)
        //  val username=intent.getStringExtra("username")
      *//*  val plan_name = (plan.plan_id).toString()
        intent.putExtra("plan_name", plan_name)
        intent.putExtra("project_name", project_name)
        intent.putExtra("username", username)
        intent.putExtra("organization_name", organization_name)*//*
        startActivity(intent)
    }
*/

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    override fun onCellClickListenerPlan(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    ) {

        var intent = Intent(this, TaskActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }


}

