package com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.R

import com.example.hopelastrestart1.databinding.ActivityAssignTaskManpowerBinding
import com.example.hopelastrestart1.ui.planEngineer.Task.TaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.ApiException
import com.example.hopelastrestart1.util.NoInternetException
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AssignTaskManpower : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: TaskViewModel
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityAssignTaskManpowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_assign_task_manpower)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_assign_task_manpower)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        val skilled_labour =
            findViewById<EditText>(R.id.edit_text_skilled_labour).getText().toString()
        val skilled_worktime =
            findViewById<EditText>(R.id.edit_text_skilled_worktime).getText().toString()
        val unskilled_labour =
            findViewById<EditText>(R.id.edit_text_unskilled_labour).getText().toString()
        val unskilled_worktime =
            findViewById<EditText>(R.id.edit_text_unskilled_worktime).getText().toString()
        val material_1_quantity = intent.getStringExtra("material_1_quantity")
        val material_2_quantity = intent.getStringExtra("material_2_quantity")
        val material_3_quantity = intent.getStringExtra("material_3_quantity")
        val material_1 = intent.getStringExtra("material_1")
        val material_2 = intent.getStringExtra("material_2")
        val material_3 = intent.getStringExtra("material_3")
        val username=intent.getStringExtra("username")
        val organization_name=intent.getStringExtra("organization_name")
        val activity_id=intent.getStringExtra("activity_id")
        val activity_name=intent.getStringExtra("activity_name")
        val activity_type=intent.getStringExtra("activity_type")
        val from_node=intent.getStringExtra("from_node")
        val to_node=intent.getStringExtra("to_node")
        val task_id=intent.getStringExtra("task_id")
        val project_name=intent.getStringExtra("project_name")
        val assigned_to=intent.getStringExtra("assigned_to")
        val jcb_quantity=intent.getStringExtra("jcb_quantity")
        val hydra_quantity=intent.getStringExtra("hydra_quantity")
        val tractor_quantity=intent.getStringExtra("tractor_quantity")
        val watertanker_quantity=intent.getStringExtra("watertanker_quantity")
        val tractorcompressor_quantity=intent.getStringExtra("tractorcompressor_quantity")
        val jcb_runtime=intent.getStringExtra("jcb_runtime")
        val hydra_runtime=intent.getStringExtra("hydra_runtime")
        val tractor_runtime=intent.getStringExtra("tractor_runtime")
        val watertanker_runtime=intent.getStringExtra("watertanker_runtime")
        val tractorcompressor_runtime=intent.getStringExtra("tractorcompressor_runtime")

        //

        //
       // var btn_to_material = findViewById<Button>(R.id.btn_submit_activity_)
        binding.btnSubmitActivity.setOnClickListener {
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
            )

           val intent = Intent(this, TaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", "2")
           startActivity(intent)
        }
    }

    private fun saveAssignedTaskActivity(
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
             val x=  viewModel.submitAssignedTaskActivity(
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
                   val v=4
                }

            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }

        }

    }
}