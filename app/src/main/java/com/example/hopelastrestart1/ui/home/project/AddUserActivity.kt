package com.example.hopelastrestart1.ui.home.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAddProjectBinding
import com.example.hopelastrestart1.databinding.ActivityAddUserBinding
import com.example.hopelastrestart1.databinding.ActivityProjectBinding
import com.example.hopelastrestart1.model.AddUser
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_assign_task.*
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddUserActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var viewModel: UserViewModel
    lateinit var organization_name: String
    private lateinit var viewModel1: ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        //   binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        //  viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(
            UserViewModel::class.java
        )
        val username = intent.getStringExtra("username")
        organization_name = intent.getStringExtra("organization_name")
        val projectSpinner = findViewById<Spinner>(R.id.spinner_project)
        val roleSpinner = findViewById<Spinner>(R.id.spinner_role)
        val roles = resources.getStringArray(R.array.user_roles)
        //val projects_adapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)
        val roles_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        roleSpinner.adapter = roles_adapter

        val projects = GlobalData.getInstance.projects
        var projectsArray = arrayOf<String>()


        for (element in projects!!) {
            projectsArray = append(projectsArray, element.project_name)
        }
        val projects_adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, projectsArray)
        projectSpinner.adapter = projects_adapter

        binding.buttonAddUser.setOnClickListener {

            if (binding.etaddUserEmail.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter UserEmail")

            } else {

                val role: String = roleSpinner.getSelectedItem().toString()
                val project: String = projectSpinner.getSelectedItem().toString()
                //val name = binding.editTextName.text.toString().trim()
                //val email = binding.editTextEmail.text.toString().trim()
                val addUser = AddUser(
                    project, GlobalData.getInstance.userEmail!!,
                    GlobalData.getInstance.token!!,
                    organization_name, binding.etaddUserEmail.text.toString()
                )
                addUser(addUser)
                //     addUser(username, organization_name, name, email, project, role)

            }
        }
    }


    /*  private fun addUser(
          username: String,
          organization_name: String,
          name: String,
          email: String,
          project: String,
          role: String
      ) {
          //  val username = binding.editTextName.text.toString().trim()
          *//*
        val useremail = binding.editTextEmail.text.toString().trim()
        val userpassword = binding.editTextPassword.text.toString().trim()
        val cnfmpassword = binding.editTextPasswordConfirm.text.toString().trim()*//*
        lifecycleScope.launch {
            try {
                val addedUserResponse =
                    viewModel.addUser(username, organization_name, name, email, project, role)
                if (addedUserResponse.isSuccessful != null) {
                    //call organization activity
                    val adduser by lazyDeferred {
                        viewModel.users1(username, organization_name)
                    }
                } else {
                    val x = 3
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }*/


    private fun addUser(addUser: AddUser) {

        viewModel.addUser(addUser).observe(this, Observer {

            it?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                         response.data?.let { resp -> (resp.body()) }
                        val intent = Intent(this, ProjectActivity::class.java)
                        //intent.putExtra("username", username)
                        intent.putExtra("organization_name", organization_name)
                        startActivity(intent)
                        finish()
                    }
                    Status.ERROR -> {
                        binding.progressBar.hide()

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }
                }

            }
        })

    }

    fun append(projects: Array<String>, project: String?): Array<String> {
        val list: MutableList<String> = projects.toMutableList()
        if (project != null) {
            list.add(project)
        }
        return list.toTypedArray()

    }
}