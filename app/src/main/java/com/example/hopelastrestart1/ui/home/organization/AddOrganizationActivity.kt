package com.example.hopelastrestart1.ui.home.organization

import android.content.Intent
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
import com.example.hopelastrestart1.databinding.ActivityAddOrganizationBinding
import com.example.hopelastrestart1.databinding.ActivityAddProjectBinding
import com.example.hopelastrestart1.model.AddOrgModel
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.snackbar
import com.example.hopelastrestart1.view.BaseActivity
import kotlinx.android.synthetic.main.activity_add_organization.view.*
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AddOrganizationActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: OrganizationViewModelFactory by instance()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivityAddOrganizationBinding
    private lateinit var viewModel: OrganizationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAddOrganizationBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        // setSupportActionBar(toolbar)\
        //  binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        //////   val rootView2: View = layoutInflater.inflate(R.layout.activity_add_organization, null)

        //// binding = DataBindingUtil.bind(rootView2)!!
        //  val rootView: View = layoutInflater.inflate(R.layout.activity_add_organization, frameLayout)

        /* binding.buttonAddOrganization.setOnClickListener{
             var headerView = navigationView.getHeaderView(0)

         }*/

        // binding = DataBindingUtil.bind(rootView2)!!
        //  binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProvider(this, factory).get(OrganizationViewModel::class.java)
        var headerView = navigationView.getHeaderView(0)
        //  val username = intent.getStringExtra("username")
        headerView.tv_nav_header_title.text = GlobalData.getInstance.userEmail

        binding.buttonAddOrganization.setOnClickListener {

            if (binding.editTextOrganizationName.text.isEmpty()) {
                binding.rootLayout.snackbar("Please Enter Organization")
            } else if (binding.editTextOrganizationEmail.text.isEmpty()) {
                binding.rootLayout.snackbar("Please Enter Email")
            } else if (!binding.editTextOrganizationEmail.text.matches(emailPattern.toRegex())) {
                binding.rootLayout.snackbar("Please Enter Valid Email")
            } else if (binding.editTextOrganizationCity.text.isEmpty()) {
                binding.rootLayout.snackbar("Please Enter City")
            } else {
                val addOrgModel = AddOrgModel(
                    GlobalData.getInstance.userEmail!!,
                    GlobalData.getInstance.token!!,
                    binding.editTextOrganizationName.text.toString().trim(),
                    binding.editTextOrganizationEmail.text.toString().trim(),
                    binding.editTextOrganizationCity.text.toString().trim()
                )
                addOrganization(addOrgModel)
            }

        }


    }


    private fun addOrganization(addOrgModel: AddOrgModel) {
        lifecycleScope.launch {
            try {
                val organizationResponse = viewModel.addOrganization(addOrgModel)
                if (organizationResponse.isSuccessful == true) {
                    val intent =
                        Intent(this@AddOrganizationActivity, OrganizationActivity::class.java)
                    // intent.putExtra("username", username)
                    startActivity(intent)
                    finish()

                } else {
                    binding.rootLayout.snackbar("ERROR")
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    /* suspend fun addOrganization(addOrgModel: AddOrgModel) {
        viewModel.addOrganization(addOrgModel).observe(this, Observer {
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
        })*/


}
