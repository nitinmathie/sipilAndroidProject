package com.example.hopelastrestart1.data.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.*
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.*
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val api: MyApi,
                     private val db: AppDatabase
) : SafeApiRequest(){
    private val SubmittedtedDprs = MutableLiveData<List<AssignedToActivity>>()
    private val tasks = MutableLiveData<List<Task>>()
    private val activities = MutableLiveData<List<Activit>>()
    private val assigned_activities = MutableLiveData<List<AssignedByActivity>>()
    private val assigned_to_me= MutableLiveData<List<AssignedToMeEntity>>()
    private val Assignedtasks = MutableLiveData<List<Task>>()
    private val Reportedtasks = MutableLiveData<List<Task>>()
    private val activit = MutableLiveData<Activit>()
    private val ccActivity = MutableLiveData<CcAct>()
    private val pipeActivity = MutableLiveData<PipeAct>()
    private val mhActivity = MutableLiveData<MhAct>()
    private val hscActivity = MutableLiveData<HscAct>()
    private val hkActivity = MutableLiveData<HkAct>()
    private val mydprs = MutableLiveData<List<DprInfo>>()
    init{
        tasks.observeForever{
            saveTasks(it)
        }    }
    init{
        mydprs.observeForever{
            saveDprs(it)
        }    }
    init{
        activities.observeForever{
            saveActivities(it)
        }    }
    init{
        ccActivity.observeForever{
            saveCcAct(it)
        }    }
    // pipe Act
    init{
        pipeActivity.observeForever{
            savePipeAct(it)
        }    }
    //mh Act
    init{
        mhActivity.observeForever{
            saveMhAct(it)
        }    }
    //hsc Act

    init{
        hscActivity.observeForever{
            saveHscAct(it)
        }    }
    //hk Act
    init{
        hkActivity.observeForever{
            saveHkAct(it)
        }    }
    init{
        assigned_activities.observeForever({
            saveAssignedActivities(it)
        })
    }
    private fun saveAssignedActivities(tasks: List<AssignedByActivity>){
        Coroutines.io{
            db.getUserAssignedTaskActivitiesDao().deleteAll()
            db.getUserAssignedTaskActivitiesDao().saveAllAssignedActivities(tasks)
        }
    }
    private fun saveTasks(tasks: List<Task>){
        Coroutines.io{
            db.getPlanTasksDao().deleteAll()
            db.getPlanTasksDao().saveAllPlanTasks(tasks)
        }
    }
    private fun saveDprs(tasks: List<DprInfo>){
        Coroutines.io{
            db.getmydprs().deleteAll()
            db.getmydprs().saveAllMyDprs(tasks)
        }
    }
    //save cc
    private fun saveCcAct(tasks: CcAct){
        Coroutines.io{
            db.getTaskCCActivitiesDao().deleteAll()
            db.getTaskCCActivitiesDao().saveCCActivity(tasks)
        }
    }
    private fun savePipeAct(tasks: PipeAct){
        Coroutines.io{
            db.getTaskPipeActivitiesDao().deleteAll()
            db.getTaskPipeActivitiesDao().savePipeActivity(tasks)
        }
    }
    private fun saveMhAct(tasks: MhAct){
        Coroutines.io{
            db.getTaskMHActivitiesDao().deleteAll()
            db.getTaskMHActivitiesDao().saveMHActivity(tasks)
        }
    }
    private fun saveHscAct(tasks: HscAct){
        Coroutines.io{
            db.getTaskHSCActivitiesDao().deleteAll()
            db.getTaskHSCActivitiesDao().saveHSCActivity(tasks)
        }
    }
    private fun saveHkAct(tasks: HkAct){
        Coroutines.io{
            db.getTaskHKActivitiesDao().deleteAll()
            db.getTaskHKActivitiesDao().saveHKActivity(tasks)
        }
    }

 //save activities
 private fun saveActivities(tasks: List<Activit>){
     Coroutines.io{
         db.getTaskActivitiesDao().deleteAll()
         db.getTaskActivitiesDao().saveActs(tasks)
     }
 }
    //fetch task activities
    private suspend fun fetchTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getTaskActivities(username, organization_name, project_name, plan_id, task_id)}
            //clear db if it exists, actually a new record can't be added with same id, but a new id is being added why?
            val activitys = response.task_activities
            activities.postValue(activitys)
        }
    }

    private suspend fun fetchTasks(username: String, organization_name: String, project_name: String, plan_id: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getTasks(username, organization_name, project_name, plan_id)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            tasks.postValue(response.taskinfo)
        }
    }
    private suspend fun fetchUserAssignedTasks(username: String){
        if(isFetchNeeded()){
//            val response = apiRequest{api.getUserAssignedTasks(username, organization_name, project_name, plan_id)}
            val response = apiRequest{api.getUserAssignedTasks(username)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            assigned_activities.postValue(response.assigned_activity)
        }
    }
    //user received tasks fetch
    private suspend fun fetchactivities_assigned_tome(username: String){
        if(isFetchNeeded()){
//            val response = apiRequest{api.getUserAssignedTasks(username, organization_name, project_name, plan_id)}
            val response = apiRequest{api.getassignedtome(username)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            assigned_to_me.postValue(response.assigned__to_me)
        }
    }
    //fetch user submitted dprs
    private suspend fun fetchUsersubmittedDprs(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getusersubmitteddprs(username, organization_name, project_name, plan_id)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            SubmittedtedDprs.postValue(response.dprs)
        }
    }
    //fetch user reported tasks
    private suspend fun fetchUserReportedTasks(username: String, organization_name: String, project_name: String, plan_id: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getUserReportedTasks(username, organization_name, project_name, plan_id)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            Reportedtasks.postValue(response.taskinfo)
        }
    }
    suspend fun getTasks(username: String, organization_name: String, project_name: String, plan_id: String): LiveData<List<Task>> {
        return withContext(Dispatchers.IO){
            fetchTasks(username, organization_name, project_name, plan_id)
            db.getPlanTasksDao().getTasks()
        }
    }
    suspend fun getAssignedTasks_by_user(username: String, organization_name: String, project_name: String, plan_id: String): LiveData<List<AssignedByActivity>> {
        return withContext(Dispatchers.IO){
           // fetchUserAssignedTasks(username, organization_name, project_name, plan_id)
            fetchUserAssignedTasks(username)
            db.getUserAssignedTaskActivitiesDao().getAssignedTaskActivities()
        }
    }
    //
    suspend fun getAssignedTasks_to_me(username: String, organization_name: String, project_name: String, plan_id: String): LiveData<List<AssignedToMeEntity>> {
        return withContext(Dispatchers.IO){
            // fetchUserAssignedTasks(username, organization_name, project_name, plan_id)
            fetchactivities_assigned_tome(username)
            db.getAssignedtomeDao().getallassignedtome()
        }
    }
    //getReportedTasks
    suspend fun getReportedTasks_by_user(username: String, organization_name: String, project_name: String, plan_id: String): LiveData<List<ReportedTask>> {
        return withContext(Dispatchers.IO){
            fetchUserReportedTasks(username, organization_name, project_name, plan_id)
            db.getUserReportedTasksDao().getReportedTasks()
        }
    }
    //get dprs submitted by the user
    suspend fun getsubmittedDprs(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String): LiveData<List<DPR>> {
        return withContext(Dispatchers.IO){
            fetchUsersubmittedDprs(username, organization_name, project_name, plan_id, task_id)
            db.getUserSubmittedDprDao().getalldprs()
        }
    }
    // get task activities
    suspend fun getTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String): LiveData<List<Activit>> {
        return withContext(Dispatchers.IO){
            fetchTaskActivities(username, organization_name, project_name, plan_id, task_id)
            db.getTaskActivitiesDao().getActs()
        }

    }
    suspend fun getpipeTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String): LiveData<PipeAct> {
        return withContext(Dispatchers.IO) {
            fetchpipeTaskActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
            db.getTaskPipeActivitiesDao().getPipeActivity()
        }
    }
    suspend fun getmhTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String): LiveData<MhAct> {
        return withContext(Dispatchers.IO) {
            fetchmhTaskActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
            db.getTaskMHActivitiesDao().getmhActivity()
        }
    }
    suspend fun gethscTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String): LiveData<HscAct> {
        return withContext(Dispatchers.IO) {
            fetchhscTaskActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
            db.getTaskHSCActivitiesDao().getHSCActivity()
        }
    }
    suspend fun gethkTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String): LiveData<HkAct> {
        return withContext(Dispatchers.IO) {
            fetchhkTaskActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
            db.getTaskHKActivitiesDao().getHKActivity()
        }
    }
    suspend fun getccTaskActivities(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String): LiveData<CcAct> {
        return withContext(Dispatchers.IO) {
            fetchccTaskActivity(username, organization_name, project_name, plan_id, task_id, activity_name)
            db.getTaskCCActivitiesDao().getCCActivity()

        }
    }
    //fetch task activity
    private suspend fun fetchpipeTaskActivity(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getpipeactivity(username, organization_name, project_name, plan_id, task_id, activity_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            pipeActivity.postValue(response.activity)
        }
    }
    private suspend fun fetchccTaskActivity(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getccactivity(username, organization_name, project_name, plan_id, task_id, activity_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            ccActivity.postValue(response.activity)
        }
    }
    private suspend fun fetchhscTaskActivity(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.gethscactivity(username, organization_name, project_name, plan_id, task_id, activity_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            hscActivity.postValue(response.activity)
        }
    }
    private suspend fun fetchhkTaskActivity(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.gethkactivity(username, organization_name, project_name, plan_id, task_id, activity_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            hkActivity.postValue(response.activity)
        }
    }
    private suspend fun fetchmhTaskActivity(username: String, organization_name: String, project_name: String, plan_id: String, task_id: String, activity_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getmhactivity(username, organization_name, project_name, plan_id, task_id, activity_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            mhActivity.postValue(response.activity)
        }
    }
    private fun isFetchNeeded(): Boolean{
        return true
    }
    suspend fun PlanTasks(username: String, organization_name: String, project_name: String, plan_id: String): GetTaskResponse {
        return apiRequest { api.getTasks(username, organization_name, project_name, plan_id) }
    }
    //add project
    suspend fun addTask( task_name: String,
                         task_from_node: String,
                         task_to_node: String,
                         plan_id: String,
                         projName: String,
                         orgName: String,
                         username: String): GetTaskResponse {
        return apiRequest { api.addTask(task_name, task_from_node, task_to_node, plan_id, projName, orgName, username ) }
    }
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

    ): ReportedResponse {
        return apiRequest { api.submitMaterialReport(
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

    }
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

    ): ReportedResponse{return apiRequest { api.submitManpowerReport(
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
    ) }}
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

    ):ReportedResponse{
        return apiRequest { api.submitMachineryReport(
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
    }
    suspend fun submitAssignedTaskActivity( organization_name: String,
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
                                            unskilled_worktime: String): onActivityAssigned {
        return apiRequest { api.submitAssignedTaskActivity(
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
    }
    suspend fun updateCCActivity(
                                 pipeline_status: String,ic_status: String,upvc_status: String,mh_area: String,cc_status: String,activity_name:String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String): ccActivityResponse {
        return apiRequest {
            api.updateCCActivity(
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
                username
            )
        }
    }
    //updta pipe activity
    suspend fun updatePipeActivity(activity_name: String,
                                   trenching_pipeline: String,bedding: String,laying: String,pipe_jointing: String,back_filling: String,start_on: String,pipe_status: String,activity_id:String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String): pipeActivityResponse {
        return apiRequest {
            api.updatePipeActivity(activity_name,trenching_pipeline,bedding,laying,pipe_jointing,back_filling,start_on,pipe_status,activity_id,task_id, plan_id, project_name, organization_name, username)
        }
    }
    //update mh activity
    suspend fun updateMHActivity(activity_name: String,
                                 excavation: String,removal_excess_soil: String,dust_fill_PCC_below: String,base_erection:String,pipe_mhbase_connection:String,
                                 haunching:String, raiser_erection:String, cone_erection: String, fix_UPVC:String, back_filling: String, start_on: String,mh_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String): mhActivityResponse{
        return apiRequest {
            api.updateMHActivity(
                activity_name,excavation,removal_excess_soil,dust_fill_PCC_below,base_erection,pipe_mhbase_connection,haunching,raiser_erection,cone_erection,fix_UPVC, back_filling,start_on, mh_status, activity_id,  task_id, plan_id, project_name, organization_name, username)
        }

    }
    //update HSC activity
    suspend fun updateHSCActivity(hsc_activity_name: String,
                                  excavation_for_IC: String,PCC_below_IC: String,erection_IC: String,dust_filling: String,start_on: String,hsc_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String):hscActivityResponse {
        return apiRequest {
            api.updateHSCActivity(
                hsc_activity_name,
                excavation_for_IC,
                PCC_below_IC,
                erection_IC,
                dust_filling,
                start_on,
                hsc_status,
                activity_id,
                task_id,
                plan_id,
                project_name,
                organization_name,
                username
            )
        }
    }
    //update HK activity
    suspend fun updateHKActivity(housekeeping_activity_name: String, start_on: String,
                                 hk_status: String,activity_id: String, task_id: String, plan_id: String, project_name: String, organization_name: String, username: String):hkActivityResponse {
        return apiRequest {
            api.updateHKActivity(
                housekeeping_activity_name,start_on, hk_status,activity_id,task_id, plan_id, project_name, organization_name, username)
        }
        }
    //get all dprs submitted by me
    private suspend fun fetchMyDprs(username: String, organization_name: String, project_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.dprssubmittedbyme(username, organization_name, project_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            mydprs.postValue(response.DprInfo)
        }
    }
    suspend fun dprssubmittedbyme(
        username: String,
        organization_name: String,
        project_name: String,
        //  plan_id: String,
    ) :LiveData<List<DprInfo>> {
        return withContext(Dispatchers.IO){ fetchMyDprs(username, organization_name, project_name)
            db.getmydprs().getAllMyDprs()}
    }
    //all reports reported for the plan
    private suspend fun fetchdprsforplan(username: String, organization_name: String, project_name: String, plan_id: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.dprsreportedfortheplan(username, organization_name, project_name, plan_id)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            mydprs.postValue(response.DprInfo)
        }
    }
    suspend fun dprsreportedfortheplan(
        username: String,
        organization_name: String,
        project_name: String,
        plan_id: String,
    ) :LiveData<List<DprInfo>> {
        return withContext(Dispatchers.IO){ fetchdprsforplan(username, organization_name, project_name, plan_id)
            db.getmydprs().getAllMyDprs()}
    }


}

