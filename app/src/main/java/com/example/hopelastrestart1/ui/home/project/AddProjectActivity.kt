package com.example.hopelastrestart1.ui.home.project
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAddOrganizationBinding
import com.example.hopelastrestart1.databinding.ActivityAddProjectBinding
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.snackbar
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddProjectActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ProjectViewModelFactory by instance()
    private lateinit var binding: ActivityAddProjectBinding
    private lateinit var viewModel: ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_project)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(ProjectViewModel::class.java)
        val username=intent.getStringExtra("username")
        val organization_name=intent.getStringExtra("organization_name")
        binding.buttonAddProject.setOnClickListener {
            addProject(username,organization_name)
            val intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
    private fun addProject(username:String, organization_name: String){
        val projName = binding.editTextProjectName.text.toString().trim()
        val projType = binding.editTextProjectType.text.toString().trim()
        val projLocation = binding.editTextProjectLocation.text.toString().trim()
        val projDescription = binding.editTextProjectDescription.text.toString().trim()
        lifecycleScope.launch {
            try {
                val projectResponse = viewModel.addProject(projName, organization_name, projType, projLocation, projDescription, username)
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
        }
    }
}