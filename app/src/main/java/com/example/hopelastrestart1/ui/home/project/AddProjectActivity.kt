package com.example.hopelastrestart1.ui.home.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
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
import com.example.hopelastrestart1.databinding.ActivityProjectBinding
import com.example.hopelastrestart1.model.AddProjectModel
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.item_activities.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddProjectActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ProjectViewModelFactory by instance()
    private lateinit var binding: ActivityAddProjectBinding
    private lateinit var viewModel: ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_project)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(ProjectViewModel::class.java)
        // val username = intent.getStringExtra("username")
        binding.buttonAddProject.setOnClickListener {

            if (binding.editTextProjectName.text.toString().isEmpty()) {
                binding.rootLayout.snackbar("")
            } else if (binding.editTextProjectType.text.toString().isEmpty()) {
                binding.rootLayout.snackbar("")
            } else if (binding.editTextProjectDescription.text.toString().isEmpty()) {
                binding.rootLayout.snackbar("")
            } else if (binding.editTextProjectLocation.text.toString().isEmpty()) {
                binding.rootLayout.snackbar("")
            } else {
                val projName = binding.editTextProjectName.text.toString().trim()
                val projType = binding.editTextProjectType.text.toString().trim()
                val projLocation = binding.editTextProjectLocation.text.toString().trim()
                val projDescription = binding.editTextProjectDescription.text.toString().trim()
                val addPrjctModel = AddProjectModel(
                    GlobalData.getInstance.userEmail!!,
                    GlobalData.getInstance.token!!,
                    GlobalData.getInstance.orgName.toString(),
                    projName,
                    projType,
                    projLocation,
                    projDescription
                )

                addProjectObserver(addPrjctModel)
            }


            /* val intent = Intent(this, ProjectActivity::class.java)
             intent.putExtra("username", username)
             startActivity(intent)*/
        }
    }

    private fun addProject() {

        /* lifecycleScope.launch {
             try {
                 val projectResponse = viewModel.addProject(addPrjectModel)
                 if (projectResponse.projects != null) {
                     //call organization activity
                     val projects by lazyDeferred {
                         viewModel.projects1(username, organization_name)
                     }
                 } else {
                     binding.rootLayout.snackbar(projectResponse.message!!)
                 }
             } catch (e: ApiException) {
                 e.printStackTrace()
             } catch (e: NoInternetException) {
                 e.printStackTrace()
             }
         }*/
    }

    private fun addProjectObserver(addProjectModel: AddProjectModel) {
        viewModel.addProject(addProjectModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { }
                        if (it.data?.body()?.status_code.toString().equals("200")) {
                            Toast.makeText(
                                applicationContext,
                                "Added Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        // intent.putExtra("username", username)
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
}