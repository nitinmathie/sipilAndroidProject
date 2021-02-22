package com.example.hopelastrestart1.ui.home.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAddProjectBinding
import com.example.hopelastrestart1.databinding.ActivityAddUserBinding
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.UserViewModelFactory
import com.example.hopelastrestart1.util.*
import kotlinx.android.synthetic.main.activity_assign_task.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddUserActivity(): AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModel1: ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val projectSpinner = findViewById<Spinner>(R.id.spinner_project)
        val roleSpinner = findViewById<Spinner>(R.id.spinner_role)
        val roles= arrayOf("S.E","P.E","S.O","M.O")
        val projects_adapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)
        val roles_adapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)

        roles_adapter.add(roles.toList())
        val role: String = roleSpinner.getSelectedItem().toString()
        val projects by lazyDeferred {
            viewModel1.projects1(username, organization_name)
        }
        Coroutines.main {
            //   progress_bar.show()
            val orgs = projects.await()
            orgs.observe(this, Observer {
                //    progress_bar.hide()
                it.size.toString()
                var nums = arrayOf<String>()

                for (i in 0..it.size - 1) {
                    nums = append(nums, it[i].project_name)
                }
                projects_adapter.add(nums.toList())
            })
        }
        binding.buttonAddUser.setOnClickListener {

            val role: String = roleSpinner.getSelectedItem().toString()
            val project: String = projectSpinner.getSelectedItem().toString()
            val name = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            addUser(username, organization_name,name, email,project, role )
            val intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            startActivity(intent)
        }
    }
    private fun addUser(username : String, organization_name: String,name:String, email: String, project: String, role : String )
    {
        val username = binding.editTextName.text.toString().trim()
        /*
        val useremail = binding.editTextEmail.text.toString().trim()
        val userpassword = binding.editTextPassword.text.toString().trim()
        val cnfmpassword = binding.editTextPasswordConfirm.text.toString().trim()*/
        lifecycleScope.launch {
            try {
                val addedUserResponse = viewModel.addUser(username, organization_name, name, email, project, role)
                if (addedUserResponse.isSuccessful != null) {
                    //call organization activity
                    val adduser by lazyDeferred {
                        viewModel.users1(username, organization_name)
                    }
                } else {
                  val x =3
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
    fun append(projects: Array<String>, project: String?): Array<String> {
        val list: MutableList<String> = projects.toMutableList()
        if (project != null) {
            list.add(project)
        }
        return list.toTypedArray()

    }
}