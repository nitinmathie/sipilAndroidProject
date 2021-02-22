package com.example.hopelastrestart1.ui.planEngineer.Task.tabs
import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.repository.TaskRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class TaskViewModel(private val repository: TaskRepository
) : ViewModel() {
    // private  var repository: OrganizationRepository
    //val x  = repository.userOrganizations(email: String)
    val tasks by lazyDeferred {
        repository.getTasks("", "","",""
        )
    }
    suspend fun submitAssignedTaskActivity(organization_name: String,
                                           project_name: String,
                                           username: String,
                                           task_id: String,
                                           activity_name: String,
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
    )= withContext(Dispatchers.IO) { repository.submitAssignedTaskActivity(
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
        unskilled_worktime ) }
    suspend fun submitMachineryReport(
        username: String,organization_name: String,
                                           project_name: String,
                                           plan_id: String,
                                           task_id: String,
                                           activity_name: String,
                                           //activity_type: String,
                                           //assigned_to: String,
                                           jcb_quantity: String,
                                           hydra_quantity: String,
                                           tractor_quantity: String,
                                           watertanker_quantity: String,
                                           tractorcompressor_quantity: String,
                                           jcb_runtime: String,
                                           hydra_runtime: String,
                                           tractor_runtime: String,
                                           watertanker_runtime: String,
                                           tractorcompressor_runtime: String

    )= withContext(Dispatchers.IO) { repository.submitMachineryReport(
        username,
        organization_name,
        project_name,
        plan_id,
        task_id,
        activity_name,
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
        ) }
    suspend fun submitMaterialReport(organization_name: String,
                                           project_name: String,
                                           username: String,
                                           task_id: String,
                                           activity_name: String,
                                           activity_type: String,
                                           //assigned_to: String,
                                           material_1: String,
                                           material_2: String,
                                           material_3: String,
                                           material_1_quantity: String,
                                           material_2_quantity: String,
                                           material_3_quantity: String

    )= withContext(Dispatchers.IO) { repository.submitMaterialReport(
        username,
        organization_name,
        project_name,
        task_id,
        activity_name,
        activity_type,
        //assigned_to,
        material_1,
        material_1_quantity,
        material_2,
        material_2_quantity,
        material_3,
        material_3_quantity,
         ) }
    suspend fun submitManpowerReport(username: String,
                                     organization_name: String,
                                     project_name: String,
                                     plan_id: String,
                                     task_id: String,
                                     activity_name: String,
                                     activity_type: String,
                                     skilled_labour: String,
                                     skilled_worktime:String,
                                     unskilled_labour:String,
                                     unskilled_worktime:String
        //assigned_to: String,

    )= withContext(Dispatchers.IO) { repository.submitManpowerReport(
        username,
        organization_name,
        project_name,
        plan_id,
        task_id,
        activity_name,
        activity_type,
        //assigned_to,
        skilled_labour,
        skilled_worktime,
        unskilled_labour,
        unskilled_worktime
    ) }
    suspend fun tasks1(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
    ) = withContext(Dispatchers.IO) { repository.getTasks(username , organization_name , project_name, plan_id ) }
//get all dprs submitted by me
    suspend fun dprssubmittedbyme(
        username: String,
        organization_name: String,
        project_name: String,
      //  plan_id: String,
    ) = withContext(Dispatchers.IO) { repository.dprssubmittedbyme(username , organization_name , project_name ) }
    suspend fun dprsreportedfortheplan(
        username: String,
        organization_name: String,
        project_name: String,
          plan_id: String,
    ) = withContext(Dispatchers.IO) { repository.dprsreportedfortheplan(username , organization_name , project_name, plan_id ) }
// get all the taks assigned by a user
suspend fun assignTasks1(
    username: String,
    organization_name: String,
    project_name: String,
    plan_id: String,
) = withContext(Dispatchers.IO) { repository.getAssignedTasks_by_user(username , organization_name , project_name, plan_id ) }
//get all assigned to a user
suspend fun assignTasks2(
    username: String,
    organization_name: String,
    project_name: String,
    plan_id: String,
) = withContext(Dispatchers.IO) { repository.getAssignedTasks_to_me(username , organization_name , project_name, plan_id ) }
    // get all the taks reported by a user
    suspend fun reportTasks1(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
    ) = withContext(Dispatchers.IO) { repository.getReportedTasks_by_user(username , organization_name , project_name, plan_id ) }

    //adding an task
    suspend fun addTask(taskName: String,
                        fromNode: String,
                        toNode: String,
                        planId: String,
                        projName: String,
                        orgName: String,
                        username: String
    ) = withContext(Dispatchers.IO) { repository.addTask(taskName,
        fromNode,
        toNode,
        planId,
        projName,
        orgName,
        username
        ) }
    //submit dpr

    suspend fun getsubmittedDprs(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String
    ) = withContext(Dispatchers.IO) { repository.getsubmittedDprs(username , organization_name , project_name, plan_id, task_id ) }
    //get all activities associated with a task
    suspend fun activities1(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,

    ) = withContext(Dispatchers.IO) { repository.getTaskActivities(username , organization_name , project_name, plan_id, task_id ) }
    suspend fun activitiescc(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_name: String
    ) = withContext(Dispatchers.IO) { repository.getccTaskActivities(username , organization_name , project_name, plan_id, task_id,activity_name ) }
    suspend fun activitiespipe(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_name: String
    ) = withContext(Dispatchers.IO) { repository.getpipeTaskActivities(username , organization_name , project_name, plan_id, task_id, activity_name ) }

    suspend fun activitieshsc(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_name: String
    ) = withContext(Dispatchers.IO) { repository.gethscTaskActivities(username , organization_name , project_name, plan_id, task_id, activity_name ) }
    suspend fun activitiesmh(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_name: String
    ) = withContext(Dispatchers.IO) { repository.getmhTaskActivities(username , organization_name , project_name, plan_id, task_id, activity_name ) }
    suspend fun activitieshk(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
        task_id: String,
        activity_name: String
    ) = withContext(Dispatchers.IO) { repository.gethkTaskActivities(username , organization_name , project_name, plan_id, task_id, activity_name ) }

    //update cc activity
    suspend fun updateCCActivity(
        pipeline_status: String,ic_status: String,upvc_status: String,mh_area: String,cc_status: String,activity_name:String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
     = withContext(Dispatchers.IO) { repository.updateCCActivity(
        pipeline_status,
        ic_status,
        upvc_status,
        mh_area,
        cc_status,
        activity_name,
        task_id,
        plan_id,
        project_name,
        organization_name,
        username)
             }
    //update pipe activity

    suspend fun updatePipeActivity(activity_name: String,
                                   trenching_pipeline: String,bedding: String,laying: String,pipe_jointing: String,back_filling: String,start_on: String,pipe_status: String,activity_id:String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
            = withContext(Dispatchers.IO) { repository.updatePipeActivity(
        activity_name,trenching_pipeline,bedding,laying,pipe_jointing,back_filling,start_on,pipe_status,activity_id,task_id, plan_id, project_name, organization_name, username)
    }
    //update MH activity
    suspend fun updateMHActivity(activity_name: String,
                                 excavation: String,removal_excess_soil: String,dust_fill_PCC_below: String,base_erection:String,pipe_mhbase_connection:String,
                                 haunching:String, raiser_erection:String, cone_erection: String, fix_UPVC:String, back_filling: String, start_on: String,mh_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
            = withContext(Dispatchers.IO) { repository.updateMHActivity(
        activity_name,excavation,removal_excess_soil,dust_fill_PCC_below,base_erection,pipe_mhbase_connection,haunching,raiser_erection,cone_erection,fix_UPVC, back_filling,start_on, mh_status, activity_id,  task_id, plan_id, project_name, organization_name, username)
    }
    //update Road Restoration activity
//    suspend fun updateRRActivity(restoration_activity_name: String,
//                                  fill_with_dust: String,fill_with_concrete: String,below_road_300: String,pcc_200_mh: String,pcc_200_pl:String,
//                                  vcc_pl_200: String,
//                                  vcc_UPVC_200: String,
//                                  vcc_IC_100: String,
//                                  start_on: String,cc_status: String,activity_id: String,task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
//            = withContext(Dispatchers.IO) { repository.updateRRActivity(
//        restoration_activity_name,
//        fill_with_dust,fill_with_concrete,below_road_300,pcc_200_mh,pcc_200_pl,
//        vcc_pl_200,
//        vcc_UPVC_200,
//        vcc_IC_100,
//        start_on,cc_status,activity_id,task_id, plan_id, project_name, organization_name, username)
//    }

    //update HSC activity
    suspend fun updateHSCActivity(hsc_activity_name: String,
                                  excavation_for_IC: String,PCC_below_IC: String,erection_IC: String,dust_filling: String,start_on: String,hsc_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
            = withContext(Dispatchers.IO) { repository.updateHSCActivity(hsc_activity_name,
        excavation_for_IC,PCC_below_IC,erection_IC,dust_filling,start_on,hsc_status,activity_id, task_id, plan_id, project_name, organization_name, username)
    }
    //update HK activity

    suspend fun updateHKActivity(housekeeping_activity_name: String,start_on : String,
                                 hk_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String)
            = withContext(Dispatchers.IO) { repository.updateHKActivity(
        housekeeping_activity_name,hk_status,start_on, activity_id,task_id, plan_id, project_name, organization_name, username)
    }
}
