package com.example.hopelastrestart1.ui.siteEngineer.SeReportForms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.MachinesAndMaterilasAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityAssignTaskMaterialBinding
import com.example.hopelastrestart1.databinding.ActivityReportMaterialBinding
import com.example.hopelastrestart1.model.GetMachinesAndMaterialModel
import com.example.hopelastrestart1.model.MachinesQuantity
import com.example.hopelastrestart1.model.SubmitMachineryReport
import com.example.hopelastrestart1.model.SubmitMaterialReport
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.ui.siteEngineer.SiteHomeActivity
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ReportMaterialActivity() : BaseActivity(), KodeinAware,
    MachinesAndMaterilasAdapter.CellClickListenerMachines {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityReportMaterialBinding
    private lateinit var viewModel: com.example.hopelastrestart1.viewmodel.TaskViewModel
    lateinit var activity: Activit
    lateinit var adapter: MachinesAndMaterilasAdapter
    var materialList: MutableList<MachinesQuantity> =
        ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityReportMaterialBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)


        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(com.example.hopelastrestart1.viewmodel.TaskViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerviewMaterial.layoutManager = linearLayoutManager

        val material = MachinesQuantity("material1", "10")
        materialList.add(material)
        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_id = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        binding.btnAddMaterial.setOnClickListener {
            val machineQuantity = MachinesQuantity(
                binding.spinnerMaterial.selectedItem.toString(),
                binding.etMaterialQuantity.text.toString()
            )
            materialList.add(machineQuantity)
            binding.recyclerviewMaterial.adapter =
                MachinesAndMaterilasAdapter(materialList!!, this, username)
            (binding.recyclerviewMaterial.adapter as MachinesAndMaterilasAdapter).notifyDataSetChanged()

        }
        val work = GlobalData.getInstance.getAssignedTaskActivitesModel?.work?.skilled!!
        val machinery = GetMachinesAndMaterialModel(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            work.org_name, work.project_name, work.plan_name
        )
        getMachinesAndMaterial(machinery)

        binding.btnSubmitReport.setOnClickListener {
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!
            val hasmapmaterial = HashMap<String, String>()
            for (item in materialList) {
                hasmapmaterial.put(item.machineName.toString(), item.quantity.toString())
            }
            val jsonMaterial = JSONObject(hasmapmaterial as Map<*, *>)

            val submitMachine = SubmitMaterialReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assignedTask.work?.skilled?.org_name.toString(),
                assignedTask.work?.skilled?.project_name.toString(),
                assignedTask.work?.skilled?.plan_name.toString(),
                assignedTask.work?.skilled?.task_name.toString(),
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                jsonMaterial, assignedTask.activity_type.toString()
            )
            submitMaterialReport(submitMachine)

        }

    }

    private fun getMachinesAndMaterial(getMachinesAndMaterialModel: GetMachinesAndMaterialModel) {
        viewModel.getMaterials(getMachinesAndMaterialModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { machines ->
                            machines.body()
                            /*  var machinesArray = arrayOf<String>()
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
                              binding.spinnerMaterial.adapter = projects_adapter*/

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

    override fun onCellClickListener(machines: MachinesQuantity, username: String) {
        materialList.remove(machines)
        binding.recyclerviewMaterial.adapter?.notifyDataSetChanged()
    }

    private fun submitMaterialReport(submitMaterialReport: SubmitMaterialReport) {
        viewModel.submitMaterialReport(submitMaterialReport).observe(this, Observer {
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
}