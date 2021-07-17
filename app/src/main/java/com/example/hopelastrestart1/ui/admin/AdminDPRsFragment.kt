package com.example.hopelastrestart1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.AdminDPRsAdapter
import com.example.hopelastrestart1.adapter.AdminReportsAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.network.responses.ReportedResponse
import com.example.hopelastrestart1.data.network.responses.RolesResponse
import com.example.hopelastrestart1.model.DPRs
import com.example.hopelastrestart1.model.GetReports
import com.example.hopelastrestart1.model.Report
import com.example.hopelastrestart1.model.Units
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class AdminDPRsFragment : Fragment(), AdminDPRsAdapter.CellClickListenerAdminDPRsReports {
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: DPRs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_dprs, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        return rootView
    }

    private fun getUserReports(reports: GetReports) {
        viewModel.getDprs(reports).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { resp ->
                            resp.body()
                            response = resp.body()!!
                            var unit: Units
                            if (response != null) {
                                var reports: MutableList<Units> = ArrayList()
                                unit = Units(
                                    response.Pipelaying.quantity,
                                    response.Pipelaying.unit,
                                    "",
                                    "Pipelaying"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.ManholeErection.quantity,
                                    response.ManholeErection.unit,
                                    "",
                                    "ManholeErection"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.RoadRestoration.quantity,
                                    response.RoadRestoration.unit,
                                    "",
                                    "RoadRestoration"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.UPVC_laying.quantity,
                                    response.UPVC_laying.unit,
                                    "",
                                    "UPVC_laying"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.FlowTest.quantity,
                                    response.FlowTest.unit,
                                    "",
                                    "FlowTest"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.IC.quantity,
                                    response.IC.unit,
                                    "",
                                    "IC"
                                )
                                reports.add(unit)
                                unit = Units(
                                    response.Benching.quantity,
                                    response.Benching.unit,
                                    "",
                                    "Benching"
                                )
                                reports.add(unit)

                                recycleview.adapter = AdminDPRsAdapter(
                                    reports,
                                    this,
                                    "plan"
                                )
                                Toast.makeText(activity, "success", Toast.LENGTH_LONG)
                                    .show()

                            } else {
                                //    tvCreate.visibility = View.VISIBLE

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

    override fun onResume() {
        super.onResume()
        /* val getReports = GetReports(
             GlobalData.getInstance.userEmail!!,
             GlobalData.getInstance.token!!,
             GlobalData.getInstance.orgName.toString(),
             GlobalData.getInstance.projectName.toString(),
             GlobalData.getInstance.planName.toString(),
         )*/

        val getReports = GetReports(
            GlobalData.getInstance.userEmail.toString(),
            GlobalData.getInstance.token.toString(),
            GlobalData.getInstance.orgName.toString(),
            "",
            ""
        )
        getUserReports(getReports)
    }

    override fun onCellClickListener(unit: Units, loginType: String, approveType: String) {
        TODO("Not yet implemented")
    }
}