package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import RetrofitBuilder
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivityAssignTaskManpowerBinding
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.show
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class AssignTaskManpower : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: TaskViewModel
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAssignTaskManpowerBinding
    lateinit var workJ: String
    lateinit var ccwork: AssignWork

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

        //

        val getMachines: List<Machine>? = null


        //
        // var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_)
        binding.btnSubmitActivity.setOnClickListener {

            val hasmapmachine = LinkedHashMap<String, String>()
            val hasmapmaterial = LinkedHashMap<String, String>()
            val machines = GlobalData.getInstance.machineryList
            val materials = GlobalData.getInstance.materialList
            for (item in machines!!) {
                hasmapmachine.put(item.machineName.toString(), item.quantity.toString())
            }
            for (item in materials!!) {
                hasmapmaterial.put(item.machineName.toString(), item.quantity.toString())
            }

            val manpower = ManPower(
                binding.etSkilledCount.text.toString()
                , binding.etUnskilledCount.text.toString()
            )
            val oMapper = ObjectMapper()
            val workHashMap: MutableMap<*, *>
            if (GlobalData.getInstance.assignTaskWorkType.equals("cc")) {
                val work = GlobalData.getInstance.updateTaskActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)
            } else if (GlobalData.getInstance.assignTaskWorkType.equals("hk")) {
                val work = GlobalData.getInstance.updateHouseKeepingActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)
            } else if (GlobalData.getInstance.assignTaskWorkType.equals("hsc")) {
                val work = GlobalData.getInstance.updateHscActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)
            } else if (GlobalData.getInstance.assignTaskWorkType.equals("mh")) {
                val work = GlobalData.getInstance.updateMHActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)

            } else if (GlobalData.getInstance.assignTaskWorkType.equals("pipe")) {
                val work = GlobalData.getInstance.updatePipelineActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)
            } else if (GlobalData.getInstance.assignTaskWorkType.equals("rr")) {
                val work = GlobalData.getInstance.updateRoadRestorationActivity!!
                workHashMap = oMapper.convertValue(work, MutableMap::class.java)
                ccwork = AssignWork(workHashMap as LinkedHashMap<String, String>)
            }

            val assignActivity = AssignTaskActivityModel(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.assignedTo!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                GlobalData.getInstance.planName.toString(),
                GlobalData.getInstance.task?.task_name!!,
                GlobalData.getInstance.task?.task_id.toString(),
                GlobalData.getInstance.activity?.activity_id.toString(),
                GlobalData.getInstance.activity?.activity_type.toString(),
                GlobalData.getInstance.estimatedTimeLine.toString(),
                GlobalData.getInstance.activity?.activity_type.toString(),
                GlobalData.getInstance.activity?.activity_name.toString(),
                ccwork, hasmapmachine, hasmapmaterial, manpower
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
