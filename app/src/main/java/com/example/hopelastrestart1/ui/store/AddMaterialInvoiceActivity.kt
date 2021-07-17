package com.example.hopelastrestart1.ui.store

import RetrofitBuilder
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.InvoiceMaterialAdapter
import com.example.hopelastrestart1.adapter.MachinesAndMaterilasAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.data.network.responses.AddMachineInvoice
import com.example.hopelastrestart1.data.network.responses.AddMaterialInvoice
import com.example.hopelastrestart1.databinding.ActivityAddMachineInvoiceBinding
import com.example.hopelastrestart1.databinding.ActivityAddMaterialInvoiceBinding
import com.example.hopelastrestart1.model.GetMachinesAndMaterialModel
import com.example.hopelastrestart1.model.InvoiceMachines
import com.example.hopelastrestart1.model.InvoiceMaterial
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray


class AddMaterialInvoiceActivity : BaseActivity(),
    InvoiceMaterialAdapter.CellClickListenerInvoiceMaterials {
    private lateinit var binding: ActivityAddMaterialInvoiceBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var storeViewModel: StoreViewModel
    lateinit var activity: Activit
    lateinit var adapter: MachinesAndMaterilasAdapter
    var materialList: MutableList<InvoiceMaterial> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAddMaterialInvoiceBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)


        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)
        storeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(StoreViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerviewMachies.layoutManager = linearLayoutManager
        binding.btnSubmitMachine.setOnClickListener {
            val material = InvoiceMaterial(
                binding.spinnerMachinery.selectedItem.toString(),
                binding.etMachinePrice.text.toString(),
                binding.etMachineOwnership.text.toString(),
                binding.etMachineQuantity.text.toString(),

                )
            materialList.add(material)
            binding.recyclerviewMachies.adapter =
                InvoiceMaterialAdapter(materialList!!, this, "username")
            (binding.recyclerviewMachies.adapter as InvoiceMaterialAdapter).notifyDataSetChanged()

        }
        val machinery = GetMachinesAndMaterialModel(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            ""
        )
        getMachinesAndMaterial(machinery)
        binding.btnSubmit.setOnClickListener {
            val jsonElements = Gson().toJsonTree(materialList) as JsonArray
            val addMaterialInvoice = AddMaterialInvoice(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                "sipilnewstore",
                jsonElements, binding.etMachineInvoiceNumber.text.toString(),
                binding.etMachineVendorName.text.toString()

            )
            addMaterialInvoice(addMaterialInvoice)
            //Toast.makeText(this, "2", Toast.LENGTH_LONG).show()

        }
    }

    private fun addMaterialInvoice(addMaterialInvoice: AddMaterialInvoice) {
        storeViewModel.addMaterialInvoice(addMaterialInvoice).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { machines ->
                            machines.body()
                            if (machines.body()?.status_code.equals("201")) {
                                Toast.makeText(
                                    this,
                                    "successfully added invoice",
                                    Toast.LENGTH_LONG
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Error",
                                    Toast.LENGTH_LONG
                                ).show()
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


    private fun getMachinesAndMaterial(getMachinesAndMaterialModel: GetMachinesAndMaterialModel) {
        viewModel.getMaterials(getMachinesAndMaterialModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { machines ->
                            machines.body()
                            var machinesArray = arrayOf<String>()
                            val machines = machines.body()?.materials
                            for (element in machines!!) {
                                machinesArray = append(machinesArray, element.name)
                            }
                            val projects_adapter =
                                ArrayAdapter(
                                    this,
                                    android.R.layout.simple_expandable_list_item_1,
                                    machinesArray
                                )
                            binding.spinnerMachinery.adapter = projects_adapter

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

    fun append(projects: Array<String>, project: String?): Array<String> {
        val list: MutableList<String> = projects.toMutableList()
        if (project != null) {
            list.add(project)
        }
        return list.toTypedArray()

    }

    override fun CellClickListenerInvoiceMaterials(machines: InvoiceMaterial, username: String) {
        materialList.remove(machines)
        binding.recyclerviewMachies.adapter?.notifyDataSetChanged()
    }


}