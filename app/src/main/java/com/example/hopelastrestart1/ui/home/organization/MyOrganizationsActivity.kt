package com.example.hopelastrestart1.ui.home.organization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
import com.example.hopelastrestart1.databinding.ActivityMyOrganizationsBinding
import com.example.hopelastrestart1.databinding.ActivityOrganizationBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.kodein

class MyOrganizationsActivity : BaseActivity(), CellClickListener {
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

        setupViewModel()
        val getOrgModel =
            GetOrgModel(GlobalData.getInstance.userEmail!!, GlobalData.getInstance.token!!)
        setUpObserver(getOrgModel)
        val add = findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent = Intent(this, AddOrganizationActivity::class.java)
// intent.putExtra("username", userName)
            startActivity(intent)
        }

    }

    override fun onCellClickListener(organization: Organization, username: String) {
        val intent = Intent(this, ProjectActivity::class.java)
        intent.putExtra("organization_name", organization.organization_name)
        intent.putExtra("organization_id", organization.organization_id)
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
                        resource.data?.let { users -> retrieveList(users.body()?.organizations!!) }
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
            binding.recyclerview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }
}

