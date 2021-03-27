package com.example.hopelastrestart1.ui.siteEngineer

import RetrofitBuilder
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.databinding.ActivitySiteEngTaskMhBinding
import com.example.hopelastrestart1.model.MHWork
import com.example.hopelastrestart1.model.Skill
import com.example.hopelastrestart1.model.SubmitTaskReport
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Status
import com.example.hopelastrestart1.view.BaseActivity
import com.example.hopelastrestart1.viewmodel.TaskViewModel
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

//ENSURE all the parameters required are passed by previous activity and fetched by current activity.
class SiteEngTaskmhActivity() : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var binding: ActivitySiteEngTaskMhBinding
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentFrameLayout = findViewById(R.id.container) as FrameLayout
        binding = ActivitySiteEngTaskMhBinding.inflate(layoutInflater)
        contentFrameLayout.addView(binding!!.root)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        )
            .get(TaskViewModel::class.java)         //ENSURE - the following are passed by previous screen.
        /* val username = intent.getStringExtra("username")
         val organization_name = intent.getStringExtra("organization_name")
         val project_name = intent.getStringExtra("project_name")
         val plan_name = intent.getStringExtra("plan_name")
         val task_id = intent.getStringExtra("task_id")
         val task_name = intent.getStringExtra("task_name")
         val activity_id = intent.getStringExtra("activity_id")
         val activity_name = intent.getStringExtra("activity_name")*/
        binding.buttonSubmit.setOnClickListener {

            val activity_name = binding.editTextActivityName.text.toString().trim()
            val dust_fill_PCC_below = binding.checkboxPcc.isChecked
            val base_erection = binding.baseErection.text.toString().trim()
            val excavation = binding.editTextExcavation.text.toString().trim()
            val bedding = binding.editBedding.text.toString().trim()
            val sandBedding = binding.checkboxSandBedding.isChecked
            val baseErection = binding.checkboxBaseErection.isChecked
            val consolidation = binding.checkboxConsolidation.isChecked
            val haunching = binding.checkboxHaunching.isChecked
            val pipeJointing = binding.editTextPipejointing.text.toString().trim()
            val raiserErection = binding.editTextRaiserErection.text.toString().trim()
            val cone_erection = binding.coneErection.text.toString().trim()
            val back_filling = binding.editTextBackFilling.text.toString().trim()
            val fix_UPVC = binding.editTextFixUPVC.text.toString().trim()
            val joint_holes = binding.editJointHolesSealing.text.toString().trim()
            val open_holes = binding.editOpenHolesSealing.text.toString().trim()
            val removal_excess_soil = binding.editRemovalExcessSoil.text.toString().trim()
            val started_on = binding.editTextStartedOn.text.toString().trim()
            val mh_status = binding.editTextMhStatus.text.toString().trim()
            val assignedTask = GlobalData.getInstance.getAssignedTaskActivitesModel!!

            val mhwork = MHWork(
                assignedTask.activity_task_id.toString(),
                activity_name,
                excavation,
                bedding,
                sandBedding,
                dust_fill_PCC_below,
                baseErection,
                pipeJointing,
                haunching,
                raiserErection,
                cone_erection,
                fix_UPVC,
                joint_holes,
                open_holes,
                back_filling,
                consolidation,
                removal_excess_soil
            )
            val Gson = Gson()
            val json = Gson.toJson(mhwork)
            val obj = JSONObject(json)
            val skill = Skill(obj)

            val submitMhReport = SubmitTaskReport(
                GlobalData.getInstance.userEmail!!,
                GlobalData.getInstance.token!!,
                "", "", "", "",
                assignedTask.assigned_activity_id.toString(),
                assignedTask.sub_activity_name.toString(),
                assignedTask.activity_type.toString(),
                skill, assignedTask.activity_name.toString(),
            )
            submitMHReport(submitMhReport)

            // updateMHActivity(updatemhActivity)


            //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()
            /*  val intent = Intent(this, ActivitiesActivity::class.java)
              intent.putExtra("username", username)

              startActivity(intent)*/
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun submitMHReport(submitTaskReport: SubmitTaskReport) {
        viewModel.submitWorkReport(submitTaskReport).observe(this, Observer {
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
/* val mhAct by lazyDeferred {
     viewModel.activitiesmh(
         username,
         organization_name,
         project_name,
         plan_id,
         task_id,
         activity_name
     )
 }
 Coroutines.main {
     progress_bar.show()
     val orgs = mhAct.await()
     orgs.observe(this, Observer {
         progress_bar.hide()
         //Ensure the values are changed.
         binding.editTextActivityName.setText(it.pipe_activity_name)
         binding.editTextStartedOn.setText(it.started_on)
         binding.editTextBackFilling.setText(it.bedding)
         binding.editTextBaseErection.setText(it.back_filling)
         binding.editTextConeErection.setText(it.laying)
         binding.editTextDustFillPccBelow.setText(it.status)
         binding.editTextExcavation.setText(it.trenching_pipeline)
         binding.editTextFixUPVC.setText(it.pipe_jointing)
         binding.editTextSoilRemoval.setText(it.status)

         binding.editTextRaiserErection.setText(it.laying)
         binding.editTextMhStatus.setText(it.status)
         binding.editTextHaunching.setText(it.trenching_pipeline)
         binding.editTextPipeMhbaseConnection.setText(it.pipe_jointing)
         // it.size.toString()
         // initRecyclerView(it.toOrganizationItem())
     })
 }
 //val plan_temp = binding.editTextOrganizationLocation.text.toString().trim()

 lifecycleScope.launch {
     try {
         val taskResponse = viewModel.updateMHActivity(
             activity_name,
             excavation,
             removal_excess_soil,
             dust_fill_PCC_below,
             base_erection,
             pipe_mhbase_connection,
             haunching,
             raiser_erection,
             cone_erection,
             fix_UPVC,
             back_filling,
             started_on,
             mh_status,
             activity_id,
             task_id,
             plan_id,
             project_name,
             organization_name,
             username
         )

         //call organization activity
         val organizations by lazyDeferred {
             viewModel.tasks1(username, organization_name, project_name, plan_id)
         }

     } catch (e: ApiException) {
         e.printStackTrace()
     } catch (e: NoInternetException) {
         e.printStackTrace()
     }
 }*/

/*
    private fun updateActivity(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_id: String
    ) {
        val activity_name = binding.editTextActivityName.text.toString().trim()
        val dust_fill_PCC_below = binding.dustFillPCCBelow.text.toString().trim()
        val base_erection = binding.baseErection.text.toString().trim()
        val excavation = binding.editTextExcavation.text.toString().trim()
        val haunching = binding.editTextHaunching.text.toString().trim()
        val pipe_mhbase_connection = binding.editTextPipeMhbaseConnection.text.toString().trim()
        val back_filling = binding.editTextBackFilling.text.toString().trim()
        val cone_erection = binding.coneErection.text.toString().trim()

        val removal_excess_soil = binding.editTextSoilRemoval.text.toString().trim()
        val raiser_erection = binding.editTextRaiserErection.text.toString().trim()
        val fix_UPVC = binding.editTextFixUPVC.text.toString().trim()
        val started_on = binding.editTextStartedOn.text.toString().trim()

        val mh_status = binding.editTextMhStatus.text.toString().trim()
        //val plan_location = binding.editTextOrganizationEmail.text.toString().trim()

    }*/



