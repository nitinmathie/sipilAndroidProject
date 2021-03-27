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
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityAssignTaskMachineryBinding
import com.example.hopelastrestart1.databinding.ActivityAssignTaskMaterialBinding
import com.example.hopelastrestart1.model.GetMachines
import com.example.hopelastrestart1.model.GetMachinesAndMaterialModel
import com.example.hopelastrestart1.model.MachinesQuantity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AssignTaskMaterial : BaseActivity(), KodeinAware,
    MachinesAndMaterilasAdapter.CellClickListenerMachines {

    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAssignTaskMaterialBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    lateinit var adapter: MachinesAndMaterilasAdapter
    var materialList: MutableList<MachinesQuantity> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAssignTaskMaterialBinding.inflate(layoutInflater)
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
        binding.recyclerviewMaterial.layoutManager = linearLayoutManager

        val material = MachinesQuantity("material1", "10")
        materialList.add(material)

        val username = intent.getStringExtra("username")
        val plan_name = intent.getStringExtra("plan_name")
        val organization_name = intent.getStringExtra("organization_name")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")
        val activity_type = intent.getStringExtra("activity_type")
        val from_node = intent.getStringExtra("from_node")
        val to_node = intent.getStringExtra("to_node")
        val task_id = intent.getStringExtra("task_id")
        val project_name = intent.getStringExtra("project_name")
        val assigned_to = intent.getStringExtra("assigned_to")
        val jcb_quantity = intent.getStringExtra("jcb_quantity")
        val hydra_quantity = intent.getStringExtra("hydra_quantity")
        val tractor_quantity = intent.getStringExtra("tractor_quantity")
        val watertanker_quantity = intent.getStringExtra("watertanker_quantity")
        val tractorcompressor_quantity = intent.getStringExtra("tractorcompressor_quantity")
        val jcb_runtime = intent.getStringExtra("jcb_runtime")
        val hydra_runtime = intent.getStringExtra("hydra_runtime")
        val tractor_runtime = intent.getStringExtra("tractor_runtime")
        val watertanker_runtime = intent.getStringExtra("watertanker_runtime")
        val tractorcompressor_runtime = intent.getStringExtra("tractorcompressor_runtime")

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

        val machinery = GetMachinesAndMaterialModel(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            organization_name, project_name, plan_name
        )
        getMachinesAndMaterial(machinery)
        binding.btnNextToManPower.setOnClickListener {

            GlobalData.getInstance.materialList = materialList
            val intent = Intent(this, AssignTaskManpower::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_name)
            intent.putExtra("task_id", task_id)
            intent.putExtra("activity_name", activity_name)
            startActivity(intent)

        }

        //passing to intent
        /* var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_material)
         btn_to_material.setOnClickListener {
             val intent = Intent(this, AssignTaskManpower::class.java)
             intent.putExtra("material_1_quantity", material_1_quantity)
             intent.putExtra("material_2_quantity", material_2_quantity)
             intent.putExtra("material_3_quantity", material_3_quantity)
             intent.putExtra("material_1", material_1)
             intent.putExtra("material_2", material_2)
             intent.putExtra("material_3", material_3)
             intent.putExtra("jcb_quantity", jcb_quantity)
             intent.putExtra("hydra_quantity", hydra_quantity)
             intent.putExtra("tractor_quantity", tractor_quantity)
             intent.putExtra("watertanker_quantity", watertanker_quantity)
             intent.putExtra("tractorcompressor_quantity", tractorcompressor_quantity)
             intent.putExtra("jcb_runtime", jcb_runtime)
             intent.putExtra("hydra_runtime", hydra_runtime)
             intent.putExtra("tractor_runtime", tractor_runtime)
             intent.putExtra("watertanker_runtime", watertanker_runtime)
             intent.putExtra("tractorcompressor_runtime", tractorcompressor_runtime)
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


             startActivity(intent)
         }*/


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
}