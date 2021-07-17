package com.example.hopelastrestart1.ui.store

import android.content.Intent
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
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.*
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.data.network.responses.GetAllMachineInvoices
import com.example.hopelastrestart1.data.network.responses.GetMacAndMatDispbyMe
import com.example.hopelastrestart1.data.network.responses.MachineryDispatchResp
import com.example.hopelastrestart1.data.network.responses.MaterialDispatchResp
import com.example.hopelastrestart1.databinding.FragmentMaterialsDispListBinding
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.fragment_stores.view.*

class MachineDispFragment : Fragment(), CellClickListenerMachDisp {
    lateinit var binding: FragmentMaterialsDispListBinding
    lateinit var viewModel: StoreViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var fbBtn: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_materials_disp_list, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_material_disp)
        fbBtn = rootView.findViewById(R.id.fab_create_disp)
        tvCreate = rootView.findViewById<TextView>(R.id.tv_create)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(StoreViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager

        fbBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DispatchMachineryActivity::class.java)
            startActivity(intent)
        })
        fetchMachineryDispatchedByMe(
            GetMacAndMatDispbyMe(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                "",
                ""
            )
        )
        return rootView
    }


    private fun fetchMachineryDispatchedByMe(fmdispatchedByMe: GetMacAndMatDispbyMe) {
        viewModel.fetchMachineryDispatchedByMe(fmdispatchedByMe)
            .observe(viewLifecycleOwner, Observer {
                it.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            resource.data?.let { projects ->
                                if (projects.body() != null) {
                                    if (projects.body()?.status_code.toString().equals("200")) {
                                        val matDisp = projects.body()?.machinery_dispatches
                                        recycleview.adapter = MachineDispatchedAdapter(
                                            matDisp!!, this, "",
                                            "", ""

                                        )
                                    } else if (projects.body()?.status_code.toString()
                                            .equals("204")
                                    ) {
                                    }
                                } else {

                                }

                            }

                        }
                        Status.ERROR -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })


    }

    override fun CellClickListenerMachDisp(
        machineryDispatchResp: MachineryDispatchResp,
        username: String,
        organization_name: String,
        project_name: String
    ) {

    }


}