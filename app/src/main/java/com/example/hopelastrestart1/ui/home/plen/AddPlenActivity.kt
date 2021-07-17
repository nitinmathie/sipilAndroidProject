package com.example.hopelastrestart1.ui.home.plen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.example.hopelastrestart1.databinding.ActivityAddPlanBinding
import com.example.hopelastrestart1.databinding.ActivityAddPlenBinding
import com.example.hopelastrestart1.model.AddPlan
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.ui.home.plen.tabs.PlanViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.activity_add_plen.view.*
import kotlinx.android.synthetic.main.activity_organization.*
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
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(
            PlanViewModel
            ::class.java
        )
        //val username = intent.getStringExtra("username")

        binding.buttonAddPlan.setOnClickListener {
            val addPlan = AddPlan(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                binding.editTextPlanName.text.toString(),
                binding.editTextPlanDescription.text.toString()
            )
            setUpObserver(addPlan)
            /*  val intent = Intent(this, PlenActivity::class.java)
              //intent.putExtra("username", username)
              startActivity(intent)*/
        }
    }

    /*   private fun addPlan(username: String, organization_name: String, project_name: String) {
           val plan_name = binding.editTextPlanName.text.toString().trim()
           val plan_desc = binding.editTextPlanDescription.text.toString().trim()
           val plan_temp = binding.editTextPlanTemplate.text.toString().trim()

           //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
           //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

           lifecycleScope.launch {
               try {
                   val planResponse = viewModel.addPlan(
                       plan_name,
                       plan_desc,
                       plan_temp,
                       project_name,
                       organization_name,
                       username
                   )
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
       }*/

    private fun setUpObserver(addPlan: AddPlan) {
        viewModel.addPlan(addPlan).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { planResponse ->
                            if (planResponse.body() != null) {
                                if (planResponse.body()?.isSuccessful!!) {
                                    if (planResponse.body()?.status_code!!.toString()
                                            .equals("200")
                                    ) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Successfully Created",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        finish()
                                    } else {
                                        binding.root.snackbar("Error")
                                    }
                                } else {
                                    binding.root.snackbar("Error")
                                }
                            } else {
                                binding.root.snackbar("Error")
                            }

                        }
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
