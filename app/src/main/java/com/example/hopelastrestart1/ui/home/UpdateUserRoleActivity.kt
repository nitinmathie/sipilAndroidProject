package com.example.hopelastrestart1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.hopelastrestart1.GlobalData
import kotlinx.android.synthetic.main.activity_home.*
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityHomeBinding
import com.example.hopelastrestart1.databinding.ActivityUpdateProfileBinding
import com.example.hopelastrestart1.model.UpdateProfile
import com.example.hopelastrestart1.model.UpdateUserRoles
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware

class UpdateUserRoleActivity : BaseActivity() {
    private lateinit var viewModel: UserViewModel
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)

        var nums = arrayOf<String>()
        var projectsArray = arrayOf<String>()

        val projects = GlobalData.getInstance.projects
        for (element in projects!!) {
            projectsArray = append(projectsArray, element.project_name)
        }
        val projects_adapter =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, projectsArray)
        binding.spinnerSelectProject.adapter = projects_adapter

        val roles = resources.getStringArray(R.array.user_roles)
        val roles_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        binding.spinnerUserRole.adapter = roles_adapter
        binding.btnUpdateRoles.setOnClickListener {
            val selectedItem = 1 + binding.spinnerUserRole.selectedItemPosition
            val roles = UpdateUserRoles(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                intent.getStringExtra("organization_name"),
                GlobalData.getInstance.user?.user_email!!,
                binding.spinnerSelectProject.selectedItem.toString(),
                selectedItem.toString()
            )
            updateUserRoles(roles)

        }


    }

    fun append(projects: Array<String>, project: String?): Array<String> {
        val list: MutableList<String> = projects.toMutableList()
        if (project != null) {
            list.add(project)
        }
        return list.toTypedArray()

    }

    private fun updateUserRoles(updateRoles: UpdateUserRoles) {
        viewModel.updateUserRoles(updateRoles).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects -> projects.body() }
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

}
