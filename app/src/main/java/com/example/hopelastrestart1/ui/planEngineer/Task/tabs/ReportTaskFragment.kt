package com.example.hopelastrestart1.ui.planEngineer.Task.tabs
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
import com.example.hopelastrestart1.data.db.entities.DprInfo
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.db.entities.ReportedTask
import com.example.hopelastrestart1.model.GetReports
import com.example.hopelastrestart1.model.Report
import com.example.hopelastrestart1.model.ReportsResponse
import com.example.hopelastrestart1.ui.planEngineer.Task.*
import com.example.hopelastrestart1.ui.home.organization.OrganizationItem
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
class ReportTaskFragment : Fragment(), KodeinAware, CellClickListenerReports {
    override val kodein by kodein()
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: ReportsResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.layouy_site_eng_reports, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val getReports = GetReports(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            "sipil", "sipil", "planone"
        )
        getUserReports(getReports)
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
                                if (response.reports_work != null) {
                                    reports.addAll(response.reports_machinery!!)
                                }
                                if (response.reports_work != null) {
                                    reports.addAll(response.reports_manpower!!)
                                }
                                if (response.reports_work != null) {
                                    reports.addAll(response.material_reports!!)
                                }

                                recycleview.adapter = ReportsAdapter(
                                    reports,
                                    this,
                                    GlobalData.getInstance.userEmail!!
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

    override fun onCellClickListener(reports: Report, username: String) {
        TODO("Not yet implemented")
    }



}




