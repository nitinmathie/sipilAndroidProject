package com.example.hopelastrestart1.ui.siteEngineer.tabs

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
import com.example.hopelastrestart1.adapter.AssignRVAdapter
import com.example.hopelastrestart1.adapter.CellClickListener_Assigntask
import com.example.hopelastrestart1.adapter.CellClickListener_RT
import com.example.hopelastrestart1.adapter.ReceivedTasksAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.data.network.responses.GetAssignedTasksResposne
import com.example.hopelastrestart1.data.network.responses.GetTaskResponse
import com.example.hopelastrestart1.model.GetAssignedTaskActivitesModel
import com.example.hopelastrestart1.model.GetTasksAssignedToMe
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SeReportForms.ReportMachineryActivity
import com.example.hopelastrestart1.ui.siteEngineer.SeReportForms.ReportManpowerActivity
import com.example.hopelastrestart1.ui.siteEngineer.SeReportForms.ReportMaterialActivity
import com.example.hopelastrestart1.ui.siteEngineer.SiteEngTaskccActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ReceivedTasksFragment : Fragment(), KodeinAware, CellClickListener_RT {

    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    lateinit var tvCreate: TextView
    lateinit var recycleview: RecyclerView
    lateinit var response: GetAssignedTasksResposne

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_received_task, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_receved_task)
        tvCreate = rootView.findViewById<TextView>(R.id.tv_create)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager


        assignedTasks(
            GetTasksAssignedToMe(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!
            )
        )



        return rootView
    }

    private fun assignedTasks(getTasks: GetTasksAssignedToMe) {
        viewModel.getAssignedTasksToMe(getTasks).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { resp ->
                            resp.body()
                            response = resp.body()!!
                            if (response != null) {
                                if (response?.assigned_activity != null) {
                                    if (response?.assigned_activity!!.size != 0) {
                                        recycleview.adapter = ReceivedTasksAdapter(
                                            context,
                                            response?.assigned_activity!!,
                                            this,
                                        )
                                        Toast.makeText(activity, "success", Toast.LENGTH_LONG)
                                            .show()
                                    } else {
                                        tvCreate.visibility = View.VISIBLE

                                    }
                                } else {
                                    tvCreate.visibility = View.VISIBLE

                                }
                            } else {
                                tvCreate.visibility = View.VISIBLE

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


    override fun onCellClickListener(
        task: GetAssignedTaskActivitesModel,
        activityType: String,
        clickType: String
    ) {
        if (activityType.equals("CC Breaking")) {
            GlobalData.getInstance.getAssignedTaskActivitesModel = task
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, ReportMachineryActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, ReportMaterialActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("manpower")) {
                val intent = Intent(activity, ReportManpowerActivity::class.java)
                startActivity(intent)
            }
        } else if (activityType.equals("Pipeline")) {
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            }
        } else if (activityType.equals("Manhole Erection")) {
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            }

        } else if (activityType.equals("Road Restoration")) {
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            }

        } else if (activityType.equals("House Service Connection")) {
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            }
        } else if (activityType.equals("House Keeping")) {
            if (clickType.equals("work")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("machine")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            } else if (clickType.equals("material")) {
                val intent = Intent(activity, SiteEngTaskccActivity::class.java)
                startActivity(intent)
            }

        }

    }

}