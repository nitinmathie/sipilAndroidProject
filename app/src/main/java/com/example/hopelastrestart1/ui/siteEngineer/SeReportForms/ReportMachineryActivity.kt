package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

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
import com.example.hopelastrestart1.adapter.MachinesAndMaterilasAdapter
import com.example.hopelastrestart1.adapter.MachinesSTandETAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityReportMachineryBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportMachineryActivity() : BaseActivity(), KodeinAware,
    MachinesSTandETAdapter.CellClickListenerSTandET,
    MachinesAndMaterilasAdapter.CellClickListenerMachines {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportMachineryBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    lateinit var adapter: MachinesAndMaterilasAdapter
    var machinesList: MutableList<MachinesStartAndEndTime> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityReportMachineryBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerviewMachies.layoutManager = linearLayoutManager
        val linearLayoutManagerUpdated = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerviewUpdatedMachines.layoutManager = linearLayoutManagerUpdated

        val work =
            GlobalData.getInstance.getAssignedTaskActivitesModel!!.work!!.skilled!!
        val machinery = GetMachinesAndMaterialModel(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            work.organization_name, work.project_name, work.plan_name
        )
        getMachinesAndMaterial(machinery)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")

        var machinesListUpdated: MutableList<MachinesQuantity> = ArrayList()
        var machinesListupa = GlobalData.getInstance.getAssignedTaskActivitesModel?.machinery
        var hashMap = machinesListupa!!


        val keyList: List<String> = ArrayList<String>(hashMap.keys)
        val valueList: List<String> = ArrayList<String>(hashMap.values)
        for (i in keyList.indices) {
            val machineQuantity = MachinesQuantity(
                keyList[i].toString(), valueList[i].toString()
            )
            machinesListUpdated.add(machineQuantity)
        }
        binding.recyclerviewUpdatedMachines.adapter =
            MachinesAndMaterilasAdapter(machinesListUpdated!!, this, "updated")
        (binding.recyclerviewUpdatedMachines.adapter as MachinesAndMaterilasAdapter).notifyDataSetChanged()

        binding.btnSubmitActivityMaterial.setOnClickListener {
            val machineQuantity = MachinesStartAndEndTime(
                binding.spinnerMachinery.selectedItem.toString(),
                binding.etStartTime.text.toString(),
                binding.etEndTime.text.toString()
            )
            machinesList.add(machineQuantity)
            binding.recyclerviewMachies.adapter =
                MachinesSTandETAdapter(machinesList!!, this, "current")
            (binding.recyclerviewMachies.adapter as MachinesSTandETAdapter).notifyDataSetChanged()

        }

        binding.btnNextToMaterial.setOnClickListener {
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val jsonObject = JSONObject()
            for (i in machinesList.indices) {
                val machine = JSONObject()
                val value = 1 + i
                machine.put("machine_name", machinesList[i].machineName)
                machine.put("start_time", machinesList[i].startTime)
                machine.put("end_time", machinesList[i].endTime)
                jsonObject.put("machineNumber" + value.toString(), machine)
            }

            val submitMachine = SubmitMachineryReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled!!.organization_name,
                assignedTask.work?.skilled!!.project_name,
                assignedTask.work?.skilled!!.plan_name,
                assignedTask.work?.skilled!!.task_name,
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                jsonObject, assignedTask.activity_name.toString()
            )
            submitMachineReport(submitMachine)


        }

//ENSURE the navigation stays on the same screen- Need some UX knowledge here to maka decision whether to navigate to Activities tab after update or stay with a success message in the same screen.


    }

    private fun getMachinesAndMaterial(getMachinesAndMaterialModel: GetMachinesAndMaterialModel) {
        viewModel.getMachines(getMachinesAndMaterialModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { machines ->
                            machines.body()
                            var machinesArray = arrayOf<String>()
                            val machines = machines.body()?.machines
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

    override fun onCellClickListener(machines: MachinesStartAndEndTime, username: String) {
        machinesList.remove(machines)
        binding.recyclerviewMachies.adapter?.notifyDataSetChanged()
    }

    private fun submitMachineReport(submitMachineryReport: SubmitMachineryReport) {
        viewModel.submitMachineReport(submitMachineryReport).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                            // val intent = Intent(this, ActivitiesActivity::class.java)
                            /*    intent.putExtra("username", username)
                                intent.putExtra("organization_name", organization_name)
                                intent.putExtra("project_name", project_name)
                                intent.putExtra("plan_name", plan_name)
                                intent.putExtra("task_name", task_name)*/
                            //    startActivity(intent)
                            finish()
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

    }

    override fun onCellClickListener(machines: MachinesQuantity, username: String) {
        TODO("Not yet implemented")
    }
}

/*
{
    "material_reports": [],
    "reports_work": [
    {
        "report_type": "work",
        "dpr_id": 4,
        "assigned_activity_id": "3",
        "activity_name": "CCBreaking1"
    },
    {
        "report_type": "work",
        "dpr_id": 4,
        "assigned_activity_id": "3",
        "activity_name": "CCBreaking1"
    }
    ],
    "reports_manpower": [],
    "reports_machinery": [
    {
        "report_type": "work",
        "dpr_id": 4,
        "assigned_activity_id": "3",
        "activity_name": "CCBreaking1"
    }
    ],
    "isSuccessful": true,
    "status_code": 200
}*/
