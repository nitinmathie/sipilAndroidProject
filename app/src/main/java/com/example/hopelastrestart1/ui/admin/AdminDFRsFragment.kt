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
import com.example.hopelastrestart1.adapter.AdminDFRsAdapter
import com.example.hopelastrestart1.adapter.AdminDPRsAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.model.DPRs
import com.example.hopelastrestart1.model.Dfrs
import com.example.hopelastrestart1.model.GetReports
import com.example.hopelastrestart1.model.Units
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*

class AdminDFRsFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: Dfrs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_dfrs, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        return rootView




        return rootView
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
        //getUserReports(getReports)
    }

    /* private fun getUserReports(reports: GetReports) {
         viewModel.getDfrs(reports).observe(viewLifecycleOwner, Observer {
             it?.let { resource ->
                 when (resource.status) {
                     Status.SUCCESS -> {
                         progress_bar.visibility = View.GONE
                         resource.data?.let { resp ->
                             resp.body()
                             response = resp.body()!!
                             var reports: MutableList<Units> = ArrayList()
                             for (i in response.x.indices) {
                                 var unit: Units
                                 unit = Units(
                                     response.x[i].Pipelaying.quantity,
                                     "100",
                                     response.x[i].Date
                                     , "Pipelaying"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.ManholeErection.quantity,
                                     response.ManholeErection.unit,
                                     "100",
                                     "ManholeErection"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.RoadRestoration.quantity,
                                     response.RoadRestoration.unit,
                                     "100",
                                     "RoadRestoration"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.UPVC_laying.quantity,
                                     response.UPVC_laying.unit,
                                     "100",
                                     "UPVC_laying"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.FlowTest.quantity,
                                     response.FlowTest.unit,
                                     "100",
                                     "FlowTest"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.IC.quantity,
                                     response.IC.unit,
                                     "100",
                                     "IC"
                                 )
                                 reports.add(unit)
                                 unit = Units(
                                     response.Benching.quantity,
                                     response.Benching.unit,
                                     "100",
                                     "Benching"
                                 )
                                 reports.add(unit)
                             }

                             recycleview.adapter = AdminDFRsAdapter(
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
 }*/

    /* override fun onCellClickListener(unit: Units, loginType: String, approveType: String) {
     }*/
}