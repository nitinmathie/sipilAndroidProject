package com.example.hopelastrestart1.ui.store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.MaterialInvoicesAdapter
import com.example.hopelastrestart1.adapter.MyMaterialInvoiceClickListner
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.network.responses.GetAllMachineInvoices
import com.example.hopelastrestart1.data.network.responses.Invoices
import com.example.hopelastrestart1.databinding.ActivityMachineListInvoicesBinding
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class MachineInvoicesListActivity : BaseActivity(), MyMaterialInvoiceClickListner {
    lateinit var binding: ActivityMachineListInvoicesBinding
    var context: Context? = null
    lateinit var viewModel: StoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        title = "Machine Invoices"
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(StoreViewModel::class.java)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityMachineListInvoicesBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.recyclerview.layoutManager = linearLayoutManager

        val postdata = GetAllMachineInvoices(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            "sipilnewstore"
        )
        getMachinesInvoices(postdata)
        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddMachineInvoiceActivity::class.java)
            startActivity(intent)

        })

    }

    private fun getMachinesInvoices(getAllMachineInvoices: GetAllMachineInvoices) {
        viewModel.getMachinesInvoices(getAllMachineInvoices).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects ->
                            if (projects.body() != null) {
                                if (projects.body()?.status_code.toString().equals("200")) {
                                    retrieveList(projects.body()?.invoices!!)
                                } else if (projects.body()?.status_code.toString().equals("204")) {
                                    binding.tvCreateOrg.visibility = View.VISIBLE
                                }
                            } else {
                                binding.tvCreateOrg.visibility = View.VISIBLE

                            }

                        }

                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })


    }

    private fun retrieveList(invoices: List<Invoices>) {
        if (invoices.size > 0) {
            binding.recyclerview.adapter =
                MaterialInvoicesAdapter(
                    invoices
                    , this
                )

            // recycleview.adapter = Organiz    ationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }

    override fun onCellClickListener(invoice: Invoices) {
        val intent = Intent(this, ViewMachineInvoiceActivity::class.java)
        GlobalData.getInstance.invoiceId = invoice.invoice_id
        startActivity(intent)
    }
    /* private fun getAllInvoices(
     ) {
         viewModel.getInvoices(
             getStore = GetStores(
                 "", "",
                 "", ""
             )
         ).observe(this, Observer {
             it?.let { resource ->
                 when (resource.status) {
                     Status.SUCCESS -> {
                         progress_bar.visibility = View.GONE
                         resource.data?.let { plans ->
                             plans.body()!!

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
     }*/
}