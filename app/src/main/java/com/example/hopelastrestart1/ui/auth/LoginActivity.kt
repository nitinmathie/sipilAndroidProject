package com.example.hopelastrestart1.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.User
import com.example.hopelastrestart1.data.network.responses.AuthResponse
import com.example.hopelastrestart1.databinding.ActivityLoginBinding
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.LoginModel
import com.example.hopelastrestart1.ui.home.organization.OrganizationActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.MainViewModel
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlin.math.log


class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var authResponse: AuthResponse
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        //  viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(UserViewModel::class.java)
        binding.buttonSignIn.setOnClickListener {
            if (binding.editTextEmail.text.length == 0) {
                binding.rootLayout.snackbar("Please Enter Email Address")
            } else if (!binding.editTextEmail.text.matches(emailPattern.toRegex())) {
                binding.rootLayout.snackbar("Please Enter Valid Email Address")
            } else if (binding.editTextPassword.text.length == 0) {
                binding.rootLayout.snackbar("Please Enter Password")
            } else {
                loginUser()
            }

        }

        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        var loginModel = LoginModel(email, password)
        //  lifecycleScope.launch {
        viewModel.login(loginModel).observe(this@LoginActivity, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        response.data?.let { resp -> resp.body() }
                        binding.progressBar.hide()
                        if (response.data?.body() != null) {
                            authResponse = response.data.body()!!
                            if (authResponse?.status_code.equals("200")) {
                                sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
                                val prefEditor: SharedPreferences.Editor = sharedPreferences.edit()
                                prefEditor.putBoolean("isLogedIn", true)
                                prefEditor.putString("email", authResponse!!.email)
                                prefEditor.putString("token", authResponse.token)
                                prefEditor.apply()
                                GlobalData.getInstance.token = authResponse.token
                                GlobalData.getInstance.userEmail = authResponse.email
                                var intent =
                                    Intent(applicationContext, OrganizationActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            } else if (authResponse?.status_code.equals("404")) {
                                binding.root.snackbar("Error Please Try After Some Time")
                            } else {
                                binding.root.snackbar("Error Please Try After Some Time")
                            }
                        } else {
                            binding.root.snackbar("Error Please Try After Some Time")
                        }
                        //  progress_bar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.progressBar.hide()
                        binding.root.snackbar("Error Please Try After Some Time")
                    }
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }
                }

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}
