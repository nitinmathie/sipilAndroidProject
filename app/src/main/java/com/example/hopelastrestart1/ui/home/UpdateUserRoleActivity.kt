package com.example.hopelastrestart1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.hopelastrestart1.GlobalData
import kotlinx.android.synthetic.main.activity_home.*
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Store
import com.example.hopelastrestart1.databinding.ActivityHomeBinding
import com.example.hopelastrestart1.databinding.ActivityUpdateProfileBinding
import com.example.hopelastrestart1.model.GetStores
import com.example.hopelastrestart1.model.UpdateProfile
import com.example.hopelastrestart1.model.UpdateUserRoles
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.util.snackbar
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import com.example.hopelastrestart1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware

class UpdateUserRoleActivity : BaseActivity() {
    private lateinit var viewModel: UserViewModel
    lateinit var binding: ActivityHomeBinding
    private lateinit var sviewModel: StoreViewModel
    private lateinit var Userstores: List<Store>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        title = "Update User Role"
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)
        sviewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(StoreViewModel::class.java)

        var nums = arrayOf<String>()
        var projectsArray = arrayOf<String>()

        val projects = GlobalData.getInstance.projects
        for (element in projects!!) {
            projectsArray = append(projectsArray, element.project_name)
        }
        val projects_adapter =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, projectsArray)
        binding.spinnerSelectProject.adapter = projects_adapter

        val roles = resources.getStringArray(R.array.user_roles)
        val roles_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        binding.spinnerUserRole.adapter = roles_adapter
        binding.btnUpdateRoles.setOnClickListener {
            if (Userstores.size != null && Userstores.size != 0) {
                val selectedItemUserRole = binding.spinnerUserRole.selectedItemPosition
                val selectedItemStore = binding.spinnerStore.selectedItemPosition
                if (binding.spinnerUserRole.selectedItem.toString()
                        .equals("Store Manager") || binding.spinnerUserRole.selectedItem.toString()
                        .equals("Store Keeper")
                ) {
                    val roles = UpdateUserRoles(
                        GlobalData.getInstance.userEmail!!,
                        GlobalData.getInstance.token!!,
                        GlobalData.getInstance.orgName.toString(),
                        GlobalData.getInstance.user?.user_email!!,
                        binding.spinnerSelectProject.selectedItem.toString(),
                        selectedItemUserRole.toString(),
                        Userstores[selectedItemStore].store_id.toString()
                    )
                    updateUserRoles(roles)
                } else {
                    val roles = UpdateUserRoles(
                        GlobalData.getInstance.userEmail!!,
                        GlobalData.getInstance.token!!,
                        GlobalData.getInstance.orgName.toString(),
                        GlobalData.getInstance.user?.user_email!!,
                        binding.spinnerSelectProject.selectedItem.toString(),
                        selectedItemUserRole.toString(),
                        ""
                    )
                    updateUserRoles(roles)

                }
            } else {
                binding.rootLayout.snackbar("Please Create Store First")
            }


        }
        val getStoreData = GetStores(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString()
        )
        getStores(getStoreData)

        binding.spinnerUserRole.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (binding.spinnerUserRole.selectedItem.toString().equals("Store Manager")||binding.spinnerUserRole.selectedItem.toString()
                            .equals("Store Keeper")) {
                        binding.llStore.visibility = View.VISIBLE
                    } else {
                        binding.llStore.visibility = View.GONE

                    }

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

    private fun updateUserRoles(updateRoles: UpdateUserRoles) {
        viewModel.updateUserRoles(updateRoles).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { projects ->
                            projects.body()
                        }
                        if (it.data?.body()?.status_code.toString().equals("200")) {
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                            finish()

                        } else {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

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


    private fun getStores(getStores: GetStores) {
        sviewModel.getStores(getStores).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { projects ->
                            if (it.data!!.body()!!.status_code.equals("200")) {
                                if (projects.body()?.stores!!.size != 0) {
                                    Userstores = projects.body()?.stores!!
                                    var storesArray = arrayOf<String>()
                                    val stores = projects.body()?.stores!!
                                    for (element in stores) {
                                        storesArray = append(storesArray, element.store_name)
                                    }
                                    val projects_adapter =
                                        ArrayAdapter(
                                            this,
                                            android.R.layout.simple_expandable_list_item_1,
                                            storesArray
                                        )
                                    binding.spinnerStore.adapter = projects_adapter
                                }

                            } else {
                                //binding.tvStores.visibility = View.VISIBLE
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
