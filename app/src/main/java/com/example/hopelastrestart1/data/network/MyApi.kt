package com.example.hopelastrestart1.data.network

import com.example.hopelastrestart1.data.network.responses.*
import com.example.hopelastrestart1.data.network.responses.UserResponse
import com.example.hopelastrestart1.model.*
//import com.example.hopelastrestart.data.network.responses.QuotesResponse

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

//ENSURE this page is organizaed properly
interface MyApi {
    //update HK response
//update HSC response
    @FormUrlEncoded
    @POST("updatehkactivity")
    suspend fun updateHKActivity(
        @Field("housekeeping_activity_name") housekeeping_activity_name: String,
        @Field("start_on") start_on: String,
        @Field("hk_status") hk_status: String,
        @Field("activity_id") activity_id: String,
        @Field("task_id") task_id: String,
        @Field("plan_id") plan_id: String,
        @Field("project_name") project_name: String,
        @Field("organization_name") organization_name: String,
        @Field("username") username: String
    ): Response<hkActivityResponse>

    //update HSC response
    @FormUrlEncoded
    @POST("updatehscactivity")
    suspend fun updateHSCActivity(
        @Field("hsc_activity_name") hsc_activity_name: String,
        @Field("excavation_for_IC") excavation_for_IC: String,
        @Field("PCC_below_IC") PCC_below_IC: String,
        @Field("erection_IC") erection_IC: String,
        @Field("dust_filling") dust_filling: String,
        @Field("start_on") start_on: String,
        @Field("hsc_status") hsc_status: String,
        @Field("activity_id") activity_id: String,
        @Field("task_id") task_id: String,
        @Field("plan_id") plan_id: String,
        @Field("project_name") project_name: String,
        @Field("organization_name") organization_name: String,
        @Field("username") username: String
    ): Response<hscActivityResponse>

    //update mh activity response


    @FormUrlEncoded
    @POST("updatemhactivity")
    suspend fun updateMHActivity(
        @Field("activity_name") activity_name: String,
        @Field("excavation") excavation: String,
        @Field("removal_excess_soil") removal_excess_soil: String,
        @Field("dust_fill_PCC_below") dust_fill_PCC_below: String,
        @Field("base_erection") base_erection: String,
        @Field("pipe_mhbase_connection") pipe_mhbase_connection: String,
        @Field("haunching") haunching: String,
        @Field("raiser_erection") raiser_erection: String,
        @Field("cone_erection") cone_erection: String,
        @Field("fix_UPVC") fix_UPVC: String,
        @Field("back_filling") back_filling: String,
        @Field("start_on") start_on: String,
        @Field("mh_status") mh_status: String,
        @Field("activity_id") activity_id: String,
        @Field("task_id") task_id: String,
        @Field("plan_id") plan_id: String,
        @Field("project_name") project_name: String,
        @Field("organization_name") organization_name: String,
        @Field("username") username: String
    ): Response<mhActivityResponse>
    //update pipe activity

    @FormUrlEncoded
    @POST("updatepipeactivity")
    suspend fun updatePipeActivity(
        @Field("activity_name") activity_name: String,
        @Field("trenching_pipeline") trenching_pipeline: String,
        @Field("bedding") bedding: String,
        @Field("laying") laying: String,
        @Field("pipe_jointing") pipe_jointing: String,
        @Field("back_filling") back_filling: String,
        @Field("start_on") start_on: String,
        @Field("pipe_status") pipe_status: String,
        @Field("activity_id") activity_id: String,
        @Field("task_id") task_id: String,
        @Field("plan_id") plan_id: String,
        @Field("project_name") project_name: String,
        @Field("organization_name") organization_name: String,
        @Field("username") username: String
    ): Response<pipeActivityResponse>

    //Update activity
    @FormUrlEncoded
        @POST("updatetaskactivity")
    suspend fun updateCCActivity(
        @Field("pipeline_status") pipeline_status: String,
        @Field("ic_status") ic_status: String,
        @Field("upvc_status") upvc_status: String,
        @Field("mh_area") mh_area: String,
        @Field("cc_status") cc_status: String,
        @Field("activity_name") activity_name: String,
        @Field("task_id") task_id: String,
        @Field("plan_id") plan_id: String,
        @Field("project_name") project_name: String,
        @Field("organization_name") organization_name: String,
        @Field("username") username: String
    ): Response<ccActivityResponse>

    //login
    @POST("login")
    suspend fun userLogin(@Body loginModel: LoginModel): Response<AuthResponse>

    //Get user Organizations
    //  @FormUrlEncoded
    @POST("getorganizations")
    suspend fun userOrganizations(
       @Body getOrgModel: GetOrgModel
    ): Response<OrganizationResponse>

    //Add organization
    @POST("addorganization")
    suspend fun addOrganization(@Body addOrgModel: AddOrgModel): Response<OrganizationResponse>

    //Projects urls
    @FormUrlEncoded
    @POST("getprojects")
    suspend fun userOrganizationProjects(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
    ): Response<ProjectResponse>

    //Add Project
     @POST("addproject")
    suspend fun addProject(
      @Body addPrjctModel: GetPrjctModel
    ): Response<ProjectResponse>

    // plan urls
//Projects urls
    @FormUrlEncoded
    @POST("getplans")
    suspend fun projectPlans(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
    ): Response<PlanResponse>

    //Add Project
    @FormUrlEncoded
    @POST("addplan")
    suspend fun addPlan(
        @Field("plan_name") planName: String,
        @Field("plan_description") planDesc: String,
        @Field("plan_template") planTemp: String,
        @Field("project_name") projName: String,
        @Field("organization_name") orgName: String,
        @Field("username") username: String,
    ): Response<PlanResponse>

    //get task Activities
// get tasks and add tasks
    @FormUrlEncoded
    @POST("gettaskactivities")
    suspend fun getTaskActivities(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String
    ): Response<GetTaskActivityResponse>

    //
    @FormUrlEncoded
    @POST("getactivity")
    suspend fun getccactivity(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,

        ): Response<ccActivityResponse>

    //pipe response
    @FormUrlEncoded
    @POST("getactivity")
    suspend fun getpipeactivity(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,

        ): Response<pipeActivityResponse>

    //mh response
    @FormUrlEncoded
    @POST("getactivity")
    suspend fun getmhactivity(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,

        ): Response<mhActivityResponse>

    //hsc response
    @FormUrlEncoded
    @POST("getactivity")
    suspend fun gethscactivity(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
    ): Response<hscActivityResponse>

    //hk response
    @FormUrlEncoded
    @POST("getactivity")
    suspend fun gethkactivity(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
    ): Response<hkActivityResponse>

    //add assignedactivity
    @FormUrlEncoded
    @POST("assigntask")
    suspend fun submitAssignedTaskActivity(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
        @Field("activity_type") activity_type: String,
        @Field("assigned_to") assigned_to: String,
        @Field("material_1") material_1: String,
        @Field("material_1_quantity") material_1_quantity: String,
        @Field("material_2") material_2: String,
        @Field("material_2_quantity") material_2_quantity: String,
        @Field("material_3") material_3: String,
        @Field("material_3_quantity") material_3_quantity: String,
        @Field("jcb_quantity") jcb_quantity: String,
        @Field("hydra_quantity") hydra_quantity: String,
        @Field("tractor_quantity") tractor_quantity: String,
        @Field("water_tanker_quantity") watertanker_quantity: String,
        @Field("tractor_compressor_quantity") tractorcompressor_quantity: String,
        @Field("jcb_hours") jcb_runtime: String,
        @Field("hydra_hours") hydra_runtime: String,
        @Field("tractor_hours") tractor_runtime: String,
        @Field("water_tanker") watertanker_runtime: String,
        @Field("tractor_compressor_hours") tractorcompressor_runtime: String,
        @Field("skilled_man_power") skilled_labour: String,
        @Field("skilled_man_hours") skilled_worktime: String,
        @Field("unskilled_man_power") unskilled_labour: String,
        @Field("unskilled_man_hours") unskilled_worktime: String,
    ): Response<onActivityAssigned>

    //submit material report
    @FormUrlEncoded
    @POST("assigntask")
    suspend fun submitMaterialReport(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
        @Field("activity_type") activity_type: String,
        @Field("material_1") material_1: String,
        @Field("material_1_quantity") material_1_quantity: String,
        @Field("material_2") material_2: String,
        @Field("material_2_quantity") material_2_quantity: String,
        @Field("material_3") material_3: String,
        @Field("material_3_quantity") material_3_quantity: String,
    ): Response<ReportedResponse>

    //submit machinery report
    @FormUrlEncoded
    @POST("assigntask")
    suspend fun submitMachineryReport(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,
        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
        @Field("jcb_quantity") jcb_quantity: String,
        @Field("hydra_quantity") hydra_quantity: String,
        @Field("tractor_quantity") tractor_quantity: String,
        @Field("water_tanker_quantity") watertanker_quantity: String,
        @Field("tractor_compressor_quantity") tractorcompressor_quantity: String,
        @Field("jcb_hours") jcb_runtime: String,
        @Field("hydra_hours") hydra_runtime: String,
        @Field("tractor_hours") tractor_runtime: String,
        @Field("water_tanker") watertanker_runtime: String,
        @Field("tractor_compressor_hours") tractorcompressor_runtime: String
    ): Response<ReportedResponse>

    //submit manpower report
    @FormUrlEncoded
    @POST("assigntask")
    suspend fun submitManpowerReport(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String,

        @Field("task_id") task_id: String,
        @Field("activity_name") activity_name: String,
        @Field("activity_type") activity_type: String,
        @Field("skilled_man_power") skilled_labour: String,
        @Field("skilled_man_hours") skilled_worktime: String,
        @Field("unskilled_man_power") unskilled_labour: String,
        @Field("unskilled_man_hours") unskilled_worktime: String,
    ): Response<ReportedResponse>

    // get tasks and add tasks
    @FormUrlEncoded
    @POST("gettask")
    suspend fun getTasks(

        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String
    ): Response<GetTaskResponse>

    //Add Task
    @FormUrlEncoded
    @POST("addtask")
    suspend fun addTask(
        @Field("task_name") taskName: String,
        @Field("task_startnode") planDesc: String,
        @Field("task_endnode") planTemp: String,
        @Field("plan_id") planId: String,
        @Field("project_name") projName: String,
        @Field("organization_name") orgName: String,
        @Field("username") username: String
    ): Response<GetTaskResponse>

    // get all tasks assigned by a user to any user
    @FormUrlEncoded
    @POST("getuserassignedtasks")
    suspend fun getUserAssignedTasks(
        @Field("username") username: String,
        // @Field("organization_name") organization_name: String,
        // @Field("project_name") project_name: String,
        // @Field("plan_id") plan_id: String
    ): Response<AssignedActivitiesResponse>

    @FormUrlEncoded
    @POST("getassignedtome")
    suspend fun getassignedtome(
        @Field("username") username: String,
        // @Field("organization_name") organization_name: String,
        // @Field("project_name") project_name: String,
        // @Field("plan_id") plan_id: String
    ): Response<AssignedToMe>

    //get reported by me
    @FormUrlEncoded
    @POST("getassignedtome")
    suspend fun dprssubmittedbyme(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        // @Field("plan_id") plan_id: String
    ): Response<MyDprs>

    //get reported by for the plan
//get reported by me
    @FormUrlEncoded
    @POST("getassignedtome")
    suspend fun dprsreportedfortheplan(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String
    ): Response<MyDprs>

    //get all tasks reported by a user
    // get all tasks assigned by a user to any user
    @FormUrlEncoded
    @POST("getuserreportedtasks")
    suspend fun getUserReportedTasks(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String
    ): Response<GetTaskResponse>

    // get user submitted dprs
    @FormUrlEncoded
    @POST("getusersubmitteddprs")
    suspend fun getusersubmitteddprs(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("plan_id") plan_id: String
    ): Response<DprListResponse>

    // Organization User urls getUserReportedTasks
    @FormUrlEncoded
    @POST("getusers")
    suspend fun organizationUsers(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
    ): Response<UserResponse>

    //Add Project
    @POST("adduser")
    suspend fun addUser(
        @Field("username") username: String,
        @Field("organization") organization: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("project") project: String,
        @Field("role") role: String,


        ): Response<UserResponse>

// store urls

    @FormUrlEncoded
    @POST("getstores")
    suspend fun organizationStores(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,

        ): Response<StoreResponse>
    //get stock

    @FormUrlEncoded
    @POST("getstock")
    suspend fun getStock(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("store_name") store_name: String,

        ): Response<StoreResponse>
    //get invoices

    @FormUrlEncoded
    @POST("getinvoices")
    suspend fun getInvoices(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("store_name") store_name: String,

        ): Response<InvoiceResponse>
//get indent

    @FormUrlEncoded
    @POST("getindents")
    suspend fun getIndents(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project_name") project_name: String,
        @Field("store_name") store_name: String,

        ): Response<IndentResponse>

    //Add Project
    //Todo add store api
    // while adding a user with store role ensure passing name of the store for which he is taking up the role of storeincharge
    @FormUrlEncoded
    @POST("addstore")
    suspend fun addStore(
        @Field("username") username: String,
        @Field("organization_name") organization_name: String,
        @Field("project") project: String,
        @Field("storeName") storeName: String,
        @Field("storeType") storeType: String,
        @Field("storeLocation") storeLocation: String,
        @Field("storeDescription") storeDescription: String

    ): Response<StoreResponse>

    @POST("signup")
    suspend fun userSignup(@Body SignUpModel: SignUpModel): Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResponse>

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val okkHttpclient = OkHttpClient.Builder().apply {
                readTimeout(20, TimeUnit.SECONDS)
                writeTimeout(20, TimeUnit.SECONDS)
                connectTimeout(20, TimeUnit.SECONDS)
            }
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(" https://superrestapi.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}

