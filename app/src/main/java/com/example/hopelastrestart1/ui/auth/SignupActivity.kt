package com.example.hopelastrestart1.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import kotlinx.coroutines.launch
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.network.responses.AuthResponse
import com.example.hopelastrestart1.databinding.ActivitySignupBinding
import com.example.hopelastrestart1.model.GetOrgProjectRoles
import com.example.hopelastrestart1.model.LoginModel
import com.example.hopelastrestart1.model.SignUpModel
import com.example.hopelastrestart1.ui.home.organization.MyOrganizationsActivity
import com.example.hopelastrestart1.ui.home.organization.OrganizationActivity
import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngActivity
import com.example.hopelastrestart1.ui.store.StoreHomeActivity
import com.example.hopelastrestart1.ui.store.StoreManagerMaterialsActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_signup.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var autRespSignUp: AuthResponse
    private lateinit var autRespLogin: AuthResponse
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java),)
        ).get(
            UserViewModel::class.java
        )
        binding.btnSignUp.setOnClickListener {

            if (binding.etSuFrstName.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter FirstName")
            } else if (binding.etSuLastName.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter LastName")
            } else if (binding.etSuEmail.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter Email")
            } else if (!binding.etSuEmail.text.matches(emailPattern.toRegex())) {
                binding.root.snackbar("Please Enter Valid Email")
            } else if (binding.etSuPwd.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter Password")
            } else {
                progress_bar.show()
                userSignup()
            }
        }
        binding.textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun userSignup() {
        val frstName = binding.etSuFrstName.text.toString().trim()
        val lastName = binding.etSuLastName.text.toString().trim()
        val email = binding.etSuEmail.text.toString().trim()
        val password = binding.etSuPwd.text.toString().trim()

        val user = SignUpModel(email, password, frstName, lastName)
        //@todo add input validations
        lifecycleScope.launch {
            viewModel.signUp(user).observe(this@SignupActivity, Observer {
                it?.let { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            response.data?.let { resp -> resp.body() }
                            binding.progressBar.hide()
                            if (response.data?.body() != null) {
                                autRespSignUp = response.data.body()!!
                                if (autRespSignUp.status_code.equals("201")) {
                                    loginUser(email, password)
                                } else if (autRespSignUp.status_code.equals("409")) {
                                    binding.root.snackbar("Already Registered With This Email")
                                } else if (autRespSignUp.status_code.equals("404")) {
                                    binding.root.snackbar("Please Enter Valid Details")
                                } else {
                                    binding.root.snackbar("Please Enter Valid Details")
                                }
                            } else {
                                binding.root.snackbar("Please Enter Valid Details")
                            }
                        }
                        Status.ERROR -> {
                            binding.progressBar.hide()
                            Toast.makeText(this@SignupActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        Status.LOADING -> {
                            binding.progressBar.show()
                        }
                    }

                }
            })
        }

        /* private fun loginUser() {
             val email = binding.editTextEmail.text.toString().trim()
             val password = binding.editTextPassword.text.toString().trim()
             var loginModel = LoginModel(email, password)
             lifecycleScope.launch {
                 try {
                     val authResponse = viewModel.userLogin(loginModel)
                     sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
                     val prefEditor: SharedPreferences.Editor = sharedPreferences.edit()
                     prefEditor.putBoolean("isLogedIn", true)
                     prefEditor.putString("email", authResponse.email)
                     prefEditor.putString("token", authResponse.token)
                     prefEditor.apply()
                     GlobalData.getInstance.token = authResponse.token
                     GlobalData.getInstance.userEmail = authResponse.email
                     var intent = Intent(applicationContext, OrganizationActivity::class.java)
                     intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                     startActivity(intent)
                     finish()
                 } catch (e: ApiException) {
                     binding.rootLayout.snackbar("Error")
                 } catch (e: NoInternetException) {
                     binding.rootLayout.snackbar("Error")
                 }
             }
         }*/
    }

    private fun loginUser(email: String, password: String) {
        var loginModel = LoginModel(email, password)
        //  lifecycleScope.launch {
        viewModel.login(loginModel).observe(this, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        response.data?.let { resp -> resp.body() }
                        binding.progressBar.hide()
                        if (response.data?.body() != null) {
                            autRespLogin = response.data.body()!!
                            if (autRespLogin?.status_code.equals("200")) {
                                sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
                                val prefEditor: SharedPreferences.Editor = sharedPreferences.edit()
                                prefEditor.putBoolean("isLogedIn", true)
                                prefEditor.putString("email", autRespLogin!!.email)
                                prefEditor.putString("token", autRespLogin.token)
                                prefEditor.apply()
                                GlobalData.getInstance.token = autRespLogin.token
                                GlobalData.getInstance.userEmail = autRespLogin.email
                                val getOrgProjectRoles = GetOrgProjectRoles(
                                    autRespLogin.email.toString(), autRespLogin.token.toString()
                                )
                                getRoles(getOrgProjectRoles)
                                /*    var intent =
                                        Intent(applicationContext, OrganizationActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                    finish()*/
                            } else if (autRespLogin?.status_code.equals("404")) {
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
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
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


    private fun getRoles(getOrgProjectRoles: GetOrgProjectRoles) {
        viewModel.getRoles(getOrgProjectRoles).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { users ->
                            val prefEditor: SharedPreferences.Editor = sharedPreferences.edit()
                            if (resource.data.body()?.status_code.equals("200")) {
                                if (users.body() != null) {
                                    if (users.body()?.organizations_projects_roles != null) {
                                        val roles = users.body()?.organizations_projects_roles
                                        if (roles?.size != 0) {
                                            for (i in roles?.indices!!) {
                                                if (roles[i].role.equals("0")) {
                                                    GlobalData.getInstance.loginRole = roles[i].role
                                                    prefEditor.putString("role", "Admin")
                                                    val intent = Intent(
                                                        applicationContext,
                                                        OrganizationActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    finish()
                                                    break
                                                } else if (roles[i].role.equals("1")) {
                                                    prefEditor.putString("role", "Plan")
                                                    GlobalData.getInstance.loginRole = roles[i].role
                                                    val intent = Intent(
                                                        applicationContext,
                                                        PlanActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    finish()
                                                    break
                                                } else if (roles[i].role.equals("2")) {
                                                    prefEditor.putString("role", "Site")
                                                    GlobalData.getInstance.loginRole = roles[i].role
                                                    val intent = Intent(
                                                        applicationContext,
                                                        SiteEngActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    finish()
                                                    break
                                                } else if (roles[i].role.equals("3")) {
                                                    prefEditor.putString("role", "StoreKeeper")
                                                    prefEditor.putString(
                                                        "storeName",
                                                        roles[i].store_name
                                                    )
                                                    GlobalData.getInstance.loginRole = roles[i].role
                                                    GlobalData.getInstance.storeName =
                                                        roles[i].store_name

                                                    val intent = Intent(
                                                        applicationContext,
                                                        StoreHomeActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    break
                                                } else if (roles[i].role.equals("4")) {
                                                    prefEditor.putString("role", "StoreManager")
                                                    prefEditor.putString(
                                                        "storeName",
                                                        roles[i].store_name
                                                    )
                                                    GlobalData.getInstance.loginRole = roles[i].role
                                                    GlobalData.getInstance.storeName =
                                                        roles[i].store_name

                                                    val intent = Intent(
                                                        applicationContext,
                                                        StoreManagerMaterialsActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    /*  val intent = Intent(
                                                          applicationContext,
                                                          OrganizationActivity::class.java
                                                      )
                                                      startActivity(intent)
                                                      break*/
                                                    break
                                                }
                                            }

                                        } else {
                                            val intent = Intent(
                                                applicationContext,
                                                MyOrganizationsActivity::class.java
                                            )
                                            startActivity(intent)
                                            finish()
                                        }
                                        // retrieveList(users.body()?.organizations_projects_roles!!)
                                    } else {
                                        // binding.tvCreateOrg.visibility = View.VISIBLE
                                    }
                                } else {
                                    // binding.tvCreateOrg.visibility = View.VISIBLE
                                }
                                prefEditor.apply()

                            } else if (resource.data.body()?.status_code.equals("404")) {
                                val intent = Intent(
                                    applicationContext,
                                    MyOrganizationsActivity::class.java
                                )
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })
    }
}
