package com.example.hopelastrestart1.ui.planEngineer.Task

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.ccActivityAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.databinding.ActivityTaskBinding
import com.example.hopelastrestart1.databinding.ActivityTaskccBinding
import com.example.hopelastrestart1.model.GetActivity
import com.example.hopelastrestart1.model.UpdateTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.activity_taskcc.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception


//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class TaskccActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    lateinit var task: Task
    lateinit var activit: Activit
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskccBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskccBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        title = "Activities"
        //  binding = DataBindingUtil.setContentView(this, R.layout.activity_taskcc)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_add_organization)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(TaskViewModel::class.java)
        val x = 0
        task = GlobalData.getInstance.task!!
        activit = GlobalData.getInstance.activity!!
        getActivity(
            GetActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                GlobalData.getInstance.planName.toString(),
                task.task_name.toString(),
                activit.activity_name.toString()
            )
        )
        binding.editTextActivityName.setText(activit.activity_name)

        binding.viewActivity.setOnClickListener {
            binding.llViewActiivty.visibility = View.VISIBLE
            binding.llUpdateActivity.visibility = View.GONE
        }
        binding.updateActivity.setOnClickListener {
            binding.llViewActiivty.visibility = View.GONE
            binding.llUpdateActivity.visibility = View.VISIBLE
        }
        binding.buttonUpdateCcActivity.setOnClickListener {

            val activity_name = binding.editTextActivityName.text.toString().trim()
            val StartedOn = binding.editTextStartedOn.text.toString().trim()
            val pipeTrench = binding.editText500PipelineTrench.text.toString().trim()
            val cc_status = binding.editTextCcStatus.text.toString().trim()
            //   val Ic_150 = binding.editTextIc150.text.toString().trim()
            val mh_area = binding.editTextMharea.text.toString().trim()
            val upvc_300 = binding.editTextUpvc300.text.toString().trim()

            val updateTask = UpdateTaskActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                GlobalData.getInstance.planName.toString(),
                task.task_name.toString(),
                activity_name,
                StartedOn,
                task.task_id.toString(),
                activity_name,
                pipeTrench,
                upvc_300,
                binding.checkboxIc500.isChecked,
                binding.checkboxManholeArea.isChecked
            )
            updateTaskCCActivity(updateTask)
        }
        binding.buttonAssignCcActivity.setOnClickListener {
            val activity_name = binding.editTextActivityName.text.toString().trim()
            val StartedOn = binding.editTextStartedOn.text.toString().trim()
            val pipeTrench = binding.editText500PipelineTrench.text.toString().trim()
            val cc_status = binding.editTextCcStatus.text.toString().trim()
            val Ic_150 = binding.editTextIc150.text.toString().trim()
            val mh_area = binding.editTextMharea.text.toString().trim()
            val upvc_300 = binding.editTextUpvc300.text.toString().trim()

            val updateTask = UpdateTaskActivity(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                GlobalData.getInstance.orgName.toString(),
                GlobalData.getInstance.projectName.toString(),
                GlobalData.getInstance.planName.toString(),
                task.task_name.toString(),
                activity_name,
                StartedOn,
                task.task_id.toString(),
                activity_name,
                pipeTrench,
                upvc_300,
                binding.checkboxIc500.isChecked,
                binding.checkboxManholeArea.isChecked
            )
            GlobalData.getInstance.updateTaskActivity = updateTask
            /*  val jsonObject: JSONObject? = null
              try {
                  jsonObject?.put("cc_task_id", task_id)
                  jsonObject?.put("ccbreaking_activity_name", activity_name)
                  jsonObject?.put("ccb_pipeline_trench_500_status", cc_status)
                  jsonObject?.put("ccb_upvc_350", upvc_300)
                  jsonObject?.put("ccb_IC_500", Ic_150)
                  jsonObject?.put("ccb_mharea_status", binding.checkboxManholeArea.isChecked)
              } catch (e: Exception) {
                  e.printStackTrace()
              }
              GlobalData.getInstance.jsonObject = jsonObject*/
            val intent = Intent(this, AssignTaskActivity::class.java)
            GlobalData.getInstance.assignTaskWorkType = "cc"
            startActivity(intent)

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun updateTaskCCActivity(updateTaskActivity: UpdateTaskActivity) {

        viewModel.updateTaskActivity(updateTaskActivity).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                            /*  val intent = Intent(this, ActivitiesActivity::class.java)
                              startActivity(intent)*/
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

    private fun getActivity(getActivity: GetActivity) {

        viewModel.getTaskActivity(getActivity).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            val hashmap = activities.body()?.activity
                            binding.etViewPipeTrench.setText(hashmap?.get("ccb_pipeline_trench_500_status"))
                            val cbtrue = hashmap?.get("ccb_mharea_status")
                            if (cbtrue.equals("true")) {
                                binding.cbViewManholeArea.isChecked = true
                            } else {
                                binding.cbViewManholeArea.isChecked = true
                            }
                            // if(hashmap?.getValue("ccb_mharea_status").e)
                            binding.editTextViewUpvc300.setText(hashmap?.get("ccb_upvc_350"))
                            binding.editTextViewIc150.setText(hashmap?.get("ccb_IC_500"))
                            //   Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                            /*  val intent = Intent(this, ActivitiesActivity::class.java)
                              startActivity(intent)*/
                            //   finish()
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
