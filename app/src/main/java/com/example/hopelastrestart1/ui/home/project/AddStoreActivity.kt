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
import com.example.hopelastrestart1.databinding.ActivityAddStoreBinding
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModelFactory
import com.example.hopelastrestart1.ui.home.project.tabs.StoreViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.StoreViewModelFactory
import com.example.hopelastrestart1.util.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddStoreActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: StoreViewModelFactory by instance()
    private lateinit var binding: ActivityAddStoreBinding
    private lateinit var viewModel: StoreViewModel
    private lateinit var viewModel1: ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_store)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(StoreViewModel::class.java)
        val username=intent.getStringExtra("username")
        val organization_name=intent.getStringExtra("organization_name")
        val projectSpinner = findViewById<Spinner>(R.id.spinner_project)
        val projects_adapter = ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item)
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
        binding.buttonAddStore.setOnClickListener {

            val storeName = binding.editTextStoreName.text.toString().trim()
            val storeType = binding.editTextStoreDescription.text.toString().trim()
            val storeLocation = binding.editTextStoreLocation.text.toString().trim()
            val storeDescription = binding.editTextStoreDescription.text.toString().trim()
            val project: String = projectSpinner.getSelectedItem().toString()
            addStore(username, organization_name,project, storeName,storeType, storeLocation,storeDescription )
            val intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            startActivity(intent)
            }
    }
    private fun addStore(username: String, organization_name: String,project: String, storeName: String,storeType: String, storeLocation: String,storeDescription: String ){
        lifecycleScope.launch {
            try {
                val storeResponse = viewModel.addStore(username, organization_name,project, storeName,storeType, storeLocation,storeDescription )
                if (storeResponse.stores != null) {
                    //call organization activity
                    val projects by lazyDeferred {
                        viewModel.stores1(username, organization_name)
                    }
                } else {
                    //binding.rootLayout.snackbar(projectResponse.message!!)
                    val x= 3
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