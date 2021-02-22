package com.example.hopelastrestart1.ui.home.organization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ActivityAddOrganizationBinding
import com.example.hopelastrestart1.databinding.ActivityLoginBinding
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.snackbar
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddOrganizationActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: OrganizationViewModelFactory by instance()

    private lateinit var binding: ActivityAddOrganizationBinding
    private lateinit var viewModel: OrganizationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_organization)
        binding =DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(OrganizationViewModel::class.java)
        val username=intent.getStringExtra("username")
        binding.buttonAddOrganization.setOnClickListener {
           addOrganization(username)
            val intent = Intent(this, OrganizationActivity::class.java)
            intent.putExtra("username", username)

            startActivity(intent)
        }
    }
    private fun addOrganization(username:String){
        val orgName = binding.editTextOrganizationName.text.toString().trim()
        val orgEmail = binding.editTextOrganizationEmail.text.toString().trim()
        val orgLocation = binding.editTextOrganizationLocation.text.toString().trim()
        lifecycleScope.launch {
            try {
                val organizationResponse = viewModel.addOrganization(orgName, orgEmail, orgLocation, username)
                if (organizationResponse.organizations != null) {
                    //call organization activity
                    val organizations by lazyDeferred {
                        viewModel.organizations1(username)
                    }
                } else {
                    binding.rootLayout.snackbar(organizationResponse.message!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }
}