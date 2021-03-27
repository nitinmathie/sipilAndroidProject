package com.example.hopelastrestart1.ui.planEngineer.Task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Activit
import com.example.hopelastrestart1.databinding.ActivityTaskRoadRestorationBinding
import com.example.hopelastrestart1.databinding.ActivityTaskpipeBinding
import com.example.hopelastrestart1.model.UpdateRoadRestorationActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.AssignDailyTask.AssignTaskActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TaskRoadRestorationActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivityTaskRoadRestorationBinding
    private lateinit var viewModel: TaskViewModel
    lateinit var activity: Activit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivityTaskRoadRestorationBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)


        val username = intent.getStringExtra("username")
        val organization_name = intent.getStringExtra("organization_name")
        val project_name = intent.getStringExtra("project_name")
        val plan_name = intent.getStringExtra("plan_name")
        val task_id = intent.getStringExtra("task_id")
        val task_name = intent.getStringExtra("task_name")
        val activity_id = intent.getStringExtra("activity_id")
        val activity_name = intent.getStringExtra("activity_name")

        binding.buttonUpdateRrActivity.setOnClickListener {

            val actvitityName = binding.activityName.text.toString().trim()
            val pipeTLength = binding.editTrenchingLengthPipe.text.toString().trim()
            val pipeTWidth = binding.etTrenchingWidthPipe.text.toString().trim()
            val pipeFillWithDust = binding.pipeFillWithDust.isChecked
            val pccPipeLength = binding.etPpcPipeTl.text.toString().trim()
            val pccPipeWidth = binding.etPpcPipeTw.text.toString().trim()
            val pccPipeDepth = binding.etPpcPipeTd.text.toString().trim()
            val vccPipeTl = binding.etVccPipeTl.text.toString().trim()
            val vccPipeTw = binding.etVccPipeTw.text.toString().trim()
            val vccPipeTd = binding.etVccPipeTd.text.toString().trim()
            val upvcPipeTl = binding.etUpvcPipeTl.text.toString().trim()
            val upvcPipeTw = binding.etUpvcPipeTw.text.toString().trim()
            val upvcPipeTd = binding.etUpvcPipeTd.text.toString().trim()
            val upvcFillWithDest = binding.cbUpvcFillWithDest.isChecked
            val upvcVccPipeTl = binding.etUpvcVccPipeTl.text.toString().trim()
            val upvcVccPipeTw = binding.etUpvcVccPipeTw.text.toString().trim()
            val upvcVccPipeTd = binding.etUpvcVccPipeTd.text.toString().trim()

            val updateRRActivity = UpdateRoadRestorationActivity(

                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
                pipeTLength,
                pipeTWidth,
                pipeFillWithDust,
                pccPipeLength,
                pccPipeWidth,
                pccPipeDepth,
                vccPipeTl,
                vccPipeTw,
                vccPipeTd,
                upvcPipeTl,
                upvcPipeTw,
                upvcFillWithDest,
                upvcVccPipeTl,
                upvcVccPipeTw,
                upvcVccPipeTd

            )
            updateRRActivity(updateRRActivity)


        }
        binding.buttonAssignRrActivity.setOnClickListener {
            val actvitityName = binding.activityName.text.toString().trim()
            val pipeTLength = binding.editTrenchingLengthPipe.text.toString().trim()
            val pipeTWidth = binding.etTrenchingWidthPipe.text.toString().trim()
            val pipeFillWithDust = binding.pipeFillWithDust.isChecked
            val pccPipeLength = binding.etPpcPipeTl.text.toString().trim()
            val pccPipeWidth = binding.etPpcPipeTw.text.toString().trim()
            val pccPipeDepth = binding.etPpcPipeTd.text.toString().trim()
            val vccPipeTl = binding.etVccPipeTl.text.toString().trim()
            val vccPipeTw = binding.etVccPipeTw.text.toString().trim()
            val vccPipeTd = binding.etVccPipeTd.text.toString().trim()
            val upvcPipeTl = binding.etUpvcPipeTl.text.toString().trim()
            val upvcPipeTw = binding.etUpvcPipeTw.text.toString().trim()
            val upvcPipeTd = binding.etUpvcPipeTd.text.toString().trim()
            val upvcFillWithDest = binding.cbUpvcFillWithDest.isChecked
            val upvcVccPipeTl = binding.etUpvcVccPipeTl.text.toString().trim()
            val upvcVccPipeTw = binding.etUpvcVccPipeTw.text.toString().trim()
            val upvcVccPipeTd = binding.etUpvcVccPipeTd.text.toString().trim()

            val updateRRActivity = UpdateRoadRestorationActivity(

                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                organization_name,
                project_name,
                plan_name,
                task_name,
                activity_name,
                task_id,
                activity_name,
                pipeTLength,
                pipeTWidth,
                pipeFillWithDust,
                pccPipeLength,
                pccPipeWidth,
                pccPipeDepth,
                vccPipeTl,
                vccPipeTw,
                vccPipeTd,
                upvcPipeTl,
                upvcPipeTw,
                upvcFillWithDest,
                upvcVccPipeTl,
                upvcVccPipeTw,
                upvcVccPipeTd

            )
            GlobalData.getInstance.updateRoadRestorationActivity = updateRRActivity

            val intent = Intent(this, AssignTaskActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)
            intent.putExtra("plan_name", plan_name)
            intent.putExtra("task_id", task_id)
            intent.putExtra("activity_name", activity_name)
            startActivity(intent)
        }

    }


    private fun updateRRActivity(updateRRActivity: UpdateRoadRestorationActivity) {
        viewModel.updateRoadRestoreActivity(updateRRActivity).observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activities ->
                            activities.body()
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
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
