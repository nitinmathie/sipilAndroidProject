package com.example.hopelastrestart1.ui.home.plen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAddPlanBinding
import com.example.hopelastrestart1.databinding.ActivityAddPlenBinding
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanViewModelFactory
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanViewModel
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.snackbar
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddPlenActivity() : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: PlanViewModelFactory by instance()
    private lateinit var binding: ActivityAddPlenBinding
    private lateinit var viewModel: PlanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plen)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(PlanViewModel::class.java)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        binding.buttonAddPlan.setOnClickListener {
            addPlan(username, organization_name , project_name)
            val intent = Intent(this, PlenActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }
    }
    private fun addPlan(username:String, organization_name: String, project_name:String ){
        val plan_name = binding.editTextPlanName.text.toString().trim()
        val plan_desc = binding.editTextPlanDescription.text.toString().trim()
        val plan_temp = binding.editTextPlanTemplate.text.toString().trim()
        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
        //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

        lifecycleScope.launch {
            try {
                val planResponse = viewModel.addPlan(plan_name, plan_desc, plan_temp, project_name, organization_name, username)
                if (planResponse.plans != null) {
                    //call organization activity
                    val organizations by lazyDeferred {
                        viewModel.plans1(username, organization_name, project_name)
                    }
                } else {
                    binding.rootLayout.snackbar(planResponse.message!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
}
