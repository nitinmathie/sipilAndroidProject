package com.example.hopelastrestart1.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListenerInvoice
import com.example.hopelastrestart1.adapter.InvoicesAdapter
import com.example.hopelastrestart1.adapter.ReceivedTasksAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.data.network.responses.GetAssignedTasksResposne
import com.example.hopelastrestart1.data.network.responses.Invoices
import com.example.hopelastrestart1.model.GetStores
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class InventoryFragment : Fragment(), CellClickListenerInvoice {
    lateinit var viewModel: StoreViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: GetAssignedTasksResposne

    lateinit var invoices: List<Invoices>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_inventory_list, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_invoices)
        tvCreate = rootView.findViewById<TextView>(R.id.tv_create)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(StoreViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager


        recycleview.adapter = InvoicesAdapter(
            /*   invoices,*/
            this, "", "", ""
        )

        return rootView
    }


    /*private fun getAllInvoices(
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
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })*/

    override fun CellClickListenerInvoice(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    ) {
        TODO("Not yet implemented")
    }


}