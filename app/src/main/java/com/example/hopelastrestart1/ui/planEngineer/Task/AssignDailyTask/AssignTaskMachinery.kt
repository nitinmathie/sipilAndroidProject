package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.MachinesAndMaterilasAdapter
import com.example.hopelastrestart1.adapter.OrganizationAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityAssignTaskMachineryBinding
import com.example.hopelastrestart1.databinding.ActivityTaskRoadRestorationBinding
import com.example.hopelastrestart1.model.GetMachinesAndMaterialModel
import com.example.hopelastrestart1.model.MachinesQuantity
import com.example.hopelastrestart1.model.UpdateRoadRestorationActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AssignTaskMachinery : BaseActivity(), KodeinAware,
    MachinesAndMaterilasAdapter.CellClickListenerMachines {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAssignTaskMachineryBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    lateinit var adapter: MachinesAndMaterilasAdapter
    var machinesList: MutableList<MachinesQuantity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAssignTaskMachineryBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        title = "Assign Task"
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        binding.recyclerviewMachies.layoutManager = linearLayoutManager
        binding.btnSubmitMachine.setOnClickListener {
            val machineQuantity = MachinesQuantity(
                binding.spinnerMachinery.selectedItem.toString(),
                binding.etMachineQuantity.text.toString()
            )
            machinesList.add(machineQuantity)
            binding.recyclerviewMachies.adapter =
                MachinesAndMaterilasAdapter(machinesList!!, this, "username")
            (binding.recyclerviewMachies.adapter as MachinesAndMaterilasAdapter).notifyDataSetChanged()

        }
        val machinery = GetMachinesAndMaterialModel(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
            GlobalData.getInstance.projectName.toString(),
            GlobalData.getInstance.planName.toString(),
        )
        getMachinesAndMaterial(machinery)
        binding.btnNextToMaterial.setOnClickListener {
            GlobalData.getInstance.machineryList = machinesList
            val intent = Intent(this, AssignTaskMaterial::class.java)
            startActivity(intent)

        }

        /*  var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_material)
          btn_to_material.setOnClickListener {
              val intent = Intent(this, AssignTaskMaterial::class.java)
         *//*     intent.putExtra("jcb_quantity", jcb_quantity)
            intent.putExtra("hydra_quantity", hydra_quantity)
            intent.putExtra("tractor_quantity", tractor_quantity)
            intent.putExtra("watertanker_quantity", watertanker_quantity)
            intent.putExtra("tractorcompressor_quantity", tractorcompressor_quantity)
            intent.putExtra("jcb_runtime", jcb_runtime)
            intent.putExtra("hydra_runtime", hydra_runtime)
            intent.putExtra("tractor_runtime", tractor_runtime)
            intent.putExtra("watertanker_runtime", watertanker_runtime)
            intent.putExtra("tractorcompressor_runtime", tractorcompressor_runtime)*//*
            intent.putExtra("activity_id", activity_id)
            intent.putExtra("activity_name", activity_name)
            intent.putExtra("activity_type", activity_type)
            intent.putExtra("task_id", task_id)
            intent.putExtra("from_node", from_node)
            intent.putExtra("to_node", to_node)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("assigned_to", assigned_to)
            intent.putExtra("material_1_quantity", material_1_quantity)
            intent.putExtra("material_2_quantity", material_2_quantity)
            intent.putExtra("material_3_quantity", material_3_quantity)

            intent.putExtra("material_1", material_1)
            intent.putExtra("material_2", material_2)
            intent.putExtra("material_3", material_3)
            intent.putExtra("assigned_to", assigned_to)
            intent.putExtra("task", task)
            intent.putExtra("activity", activity)
            startActivity(intent)
        }*/

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

    override fun onCellClickListener(machines: MachinesQuantity, username: String) {
        machinesList.remove(machines)
        binding.recyclerviewMachies.adapter?.notifyDataSetChanged()
    }
}