package com.example.hopelastrestart1.ui.home.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityUpdateProfileBinding
import com.example.hopelastrestart1.model.AddProjectModel
import com.example.hopelastrestart1.model.GetUserProfile
import com.example.hopelastrestart1.model.UpdateProfile
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.snackbar
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class UpdateProfileActivity : BaseActivity() {
    private lateinit var viewModel: UserViewModel

    lateinit var binding: ActivityUpdateProfileBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_update_profile)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)

        val getUserProfile =
            GetUserProfile(GlobalData.getInstance.userEmail!!, GlobalData.getInstance.token!!, "")
        getCurrentUserProfile(getUserProfile)
        binding.btnUpdp.setOnClickListener {
            if (binding.etUpdpFrstName.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter FirstName")
            } else if (binding.etUpdpLastName.text.toString().isEmpty()) {
                binding.root.snackbar("Please Enter LastName")
            } else {
                val updateProfile = UpdateProfile(
                    GlobalData.getInstance.userEmail!!, GlobalData.getInstance.token!!,
                    binding.etUpdpFrstName.text.toString().trim(),
                    binding.etUpdpLastName.text.toString().trim(),
                    binding.etUpdpAddresslineOne.text.toString().trim(),
                    binding.etUpdpAddresslineTwo.text.toString().trim(),
                    binding.etUpdpPincode.text.toString().trim(),
                )
                setUpObserver(updateProfile)
            }
        }
    }

    private fun setUpObserver(updateProfile: UpdateProfile) {
        viewModel.UpdateUserProfile(updateProfile).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects ->
                            projects.body()
                            if (projects.body()?.status_code.toString().equals("200")) {
                                Toast.makeText(this, "successfully updated", Toast.LENGTH_LONG)
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

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

    private fun getCurrentUserProfile(getUserProfile: GetUserProfile) {
        viewModel.getCurrentUserProfile(getUserProfile).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { usersProfile -> usersProfile.body() }
                        val userProfile = resource.data?.body()
                        binding.etUpdpFrstName.setText(userProfile?.first_name)
                        binding.etUpdpLastName.setText(userProfile?.last_name)
                        binding.etUpdpAddresslineOne.setText(userProfile?.address_line1)
                        binding.etUpdpAddresslineTwo.setText(userProfile?.address_line2)
                        binding.etUpdpPincode.setText(userProfile?.pin)

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