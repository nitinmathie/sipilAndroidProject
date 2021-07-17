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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.AdminReportsAdapter
import com.example.hopelastrestart1.adapter.CellClickListenerAdminReports
import com.example.hopelastrestart1.adapter.ReportsAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.model.GetReports
import com.example.hopelastrestart1.model.Report
import com.example.hopelastrestart1.model.ReportsResponse
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.activity_organization.progress_bar
import kotlinx.android.synthetic.main.fragment_admin_reports.view.*
import org.w3c.dom.Text

class AdminReportsFragment : Fragment(), CellClickListenerAdminReports {
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var tvReprots: TextView
    lateinit var response: ReportsResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_admin_reports, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        tvReprots = rootView.findViewById(R.id.tv_create_org)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager


        return rootView
    }

    private fun getUserReports(reports: GetReports) {
        viewModel.getReceivedReports(reports).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { resp ->
                            resp.body()
                            response = resp.body()!!
                            if (response != null) {
                                var reports: MutableList<Report> = ArrayList()
                                if (response.reports_work != null) {
                                    reports.addAll(response.reports_work!!)
                                }
                                if (response.reports_machinery != null) {
                                    reports.addAll(response.reports_machinery!!)
                                }
                                if (response.reports_manpower != null) {
                                    reports.addAll(response.reports_manpower!!)
                                }
                                if (response.material_reports != null) {
                                    reports.addAll(response.material_reports!!)
                                }
                                if (reports.size != 0) {
                                    root_layout.tv_create_org.visibility = View.GONE
                                    recycleview.adapter = AdminReportsAdapter(
                                        reports,
                                        this,
                                        "plan"
                                    )
                                } else {

                                    tvReprots.visibility = View.VISIBLE
                                }

                            } else {
                                //    tvCreate.visibility = View.VISIBLE
                                tvReprots.visibility = View.VISIBLE
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

    override fun onCellClickListener(reports: Report, loginType: String, approveType: String) {

    }

    override fun onResume() {
        super.onResume()
        val getReports = GetReports(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            ""
        )
        /*  val getReports = GetReports(
              GlobalData.getInstance.userEmail.toString(),
              GlobalData.getInstance.token.toString(),
              "sipil",
              "sipil",
              "planone"
          )*/
        /*   val getReports = GetReports(
                   "sivaprasad@gmail.com",
                   "334f0549149a45363df743fb4743a476dbb0568a",
                   "sipil",
               "sipil",
               "planone"
           )*/
        getUserReports(getReports)
    }

}