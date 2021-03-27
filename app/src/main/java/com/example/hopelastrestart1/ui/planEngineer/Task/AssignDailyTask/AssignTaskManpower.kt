package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import RetrofitBuilder
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAssignTaskManpowerBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.AddTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AssignTaskManpower : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: TaskViewModel
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAssignTaskManpowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityAssignTaskManpowerBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Assign Task"
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)

        val material_1_quantity = intent.getStringExtra("material_1_quantity")
        val material_2_quantity = intent.getStringExtra("material_2_quantity")
        val material_3_quantity = intent.getStringExtra("material_3_quantity")
        val material_1 = intent.getStringExtra("material_1")
        val material_2 = intent.getStringExtra("material_2")
        val material_3 = intent.getStringExtra("material_3")
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
        val assigned_to = GlobalData.getInstance.assignedTo
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

        //

        val getMachines: List<Machine>? = null

        getMachines?.forEach { item ->

        }


        //
        // var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_)
        binding.btnSubmitActivity.setOnClickListener {

            val work = SkilledWork(GlobalData.getInstance.updateTaskActivity!!)
            val hasmap = HashMap<String, String>()
            val hasmapmaterial = HashMap<String, String>()
            val machines = GlobalData.getInstance.machineryList
            val materials = GlobalData.getInstance.materialList
            for (item in machines!!) {
                hasmap.put(item.machineName.toString(), item.quantity.toString())
            }
            for (item in materials!!) {
                hasmapmaterial.put(item.machineName.toString(), item.quantity.toString())
            }
            val jsonMachine = JSONObject(hasmap as Map<*, *>)
            val jsonMaterial = JSONObject(hasmapmaterial as Map<*, *>)

            val manpower = ManPower(
                binding.etSkilledCount.text.toString()
                , binding.etUnskilledCount.text.toString()
            )

            /* val gson = Gson()
             val jsonMachine = gson.toJson(hasmap)
             val jsonMaterial = gson.toJson(hasmapmaterial)
             val jsonObject=JsonObject(jsonMachine)*/


            val assignActivity = AssignTaskActivityModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                assigned_to!!,
                organization_name,
                project_name,
                plan_name,
                GlobalData.getInstance.task?.task_name!!,
                GlobalData.getInstance.task?.task_id.toString(),
                GlobalData.getInstance.activity?.activity_id.toString(),
                GlobalData.getInstance.activity?.activity_type.toString(),
                GlobalData.getInstance.estimatedTimeLine.toString(),
                GlobalData.getInstance.activity?.activity_type.toString(),
                GlobalData.getInstance.activity?.activity_name.toString(),
                work, jsonMachine, jsonMaterial, manpower
            )
            assignTask(assignActivity)


            /*   val intent = Intent(this, TaskActivity::class.java)
               intent.putExtra("username", username)
               intent.putExtra("organization_name", organization_name)
               intent.putExtra("project_name", project_name)
               intent.putExtra("plan_name", "2")
               startActivity(intent)*/
        }
    }

    private fun assignTask(assignTaskActivityModel: AssignTaskActivityModel) {
        viewModel.assignTaskActivity(assignTaskActivityModel).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        resource.data?.let { responseBody ->
                            responseBody.body()

                            if (responseBody.body() != null) {
                                if (responseBody.body()!!.status_code.toString().equals("200")) {
                                    Toast.makeText(this, "Assigned Sucessfully", Toast.LENGTH_LONG)
                                        .show()
                                    val intenttask = Intent(this, TaskActivity::class.java)
                                    intenttask.putExtra(
                                        "username",
                                        intent.getStringExtra("username")
                                    )
                                    intenttask.putExtra(
                                        "organization_name",
                                        intent.getStringExtra("organization_name")
                                    )
                                    intenttask.putExtra(
                                        "project_name",
                                        intent.getStringExtra("project_name")
                                    )
                                    intenttask.putExtra(
                                        "plan_name",
                                        intent.getStringExtra("plan_name")
                                    )
                                    startActivity(intenttask)
                                    finish()
                                }

                            }

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

}
///activity end

/* private fun saveAssignedTaskActivity(
     username: String,
     organization_name: String,
     project_name: String,
     task_id: String,
     activity_id: String,
     activity_type: String,
     assigned_to: String,
     material_1: String,
     material_1_quantity: String,
     material_2: String,
     material_2_quantity: String,
     material_3: String,
     material_3_quantity: String,
     jcb_quantity: String,
     hydra_quantity: String,
     tractor_quantity: String,
     watertanker_quantity: String,
     tractorcompressor_quantity: String,
     jcb_runtime: String,
     hydra_runtime: String,
     tractor_runtime: String,
     watertanker_runtime: String,
     tractorcompressor_runtime: String,
     skilled_labour: String,
     skilled_worktime: String,
     unskilled_labour: String,
     unskilled_worktime: String
 ) {


     lifecycleScope.launch {
         try {
             val x = viewModel.submitAssignedTaskActivity(
                 username,
                 organization_name,
                 project_name,
                 task_id,
                 activity_id,
                 activity_type,
                 assigned_to,
                 material_1,
                 material_1_quantity,
                 material_2,
                 material_2_quantity,
                 material_3,
                 material_3_quantity,
                 jcb_quantity,
                 hydra_quantity,
                 tractor_quantity,
                 watertanker_quantity,
                 tractorcompressor_quantity,
                 jcb_runtime,
                 hydra_runtime,
                 tractor_runtime,
                 watertanker_runtime,
                 tractorcompressor_runtime,
                 skilled_labour,
                 skilled_worktime,
                 unskilled_labour,
                 unskilled_worktime
             )
             if (x.isSuccessful != null) {
                 //call organization activity
                 val organizations by lazyDeferred {
                     viewModel.tasks1(username, organization_name, project_name, "2")
                 }

             } else {
                 val v = 4
             }

         } catch (e: ApiException) {
             e.printStackTrace()
         } catch (e: NoInternetException) {
             e.printStackTrace()
         }

     }

 }

}
 saveAssignedTaskActivity(
 username,
 organization_name,
 project_name,
 task_id,
 activity_name,
 activity_type,
 assigned_to,
 material_1,
 material_1_quantity,
 material_2,
 material_2_quantity,
 material_3,
 material_3_quantity,
 jcb_quantity,
 hydra_quantity,
 tractor_quantity,
 watertanker_quantity,
 tractorcompressor_quantity,
 jcb_runtime,
 hydra_runtime,
 tractor_runtime,
 watertanker_runtime,
 tractorcompressor_runtime,
 skilled_labour,
 skilled_worktime,
 unskilled_labour,
 unskilled_worktime
 )*/
