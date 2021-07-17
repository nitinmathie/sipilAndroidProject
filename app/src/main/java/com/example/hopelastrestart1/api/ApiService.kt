package com.example.hopelastrestart1.api

import com.example.hopelastrestart1.data.network.responses.*
import com.example.hopelastrestart1.model.*
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("getuserorganizationroles")
    suspend fun getRoles(@Body getOrgProjectRoles: GetOrgProjectRoles): Response<RolesResponse>

    @POST("getorganizations")
    suspend fun getOrgs(@Body getOrgModel: GetOrgModel): Response<OrganizationResponse>

    @POST("addorganization")
    suspend fun addOrganization(@Body addOrgModel: AddOrgModel): Response<OrganizationResponse>

    @POST("getprojects")
    suspend fun getProjects(@Body getPrjctModel: GetPrjctModel): Response<ProjectResponse>

    @POST("addproject")
    suspend fun addProject(@Body addProjectModel: AddProjectModel): Response<ProjectResponse>

    @POST("addstore")
    suspend fun addStore(@Body addStore: AddStore): Response<StoreResponse>

    @POST("getstores")
    suspend fun getStores(@Body getStores: GetStores): Response<StoreResponse>

    @POST("updateprofile")
    suspend fun updateProfile(@Body updateProfile: UpdateProfile): Response<UserResponse>

    @POST("getcurrentuserprofile")
    suspend fun getCurrentUserProfile(@Body getUserProfile: GetUserProfile): Response<UserResponse>

    @POST("updateuserprojectrole")
    suspend fun updateUserRoles(@Body updateRoles: UpdateUserRoles): Response<UserResponse>


    @POST("getuserprofile")
    suspend fun getUserProfile(@Body getUserProfile: GetUserProfile): Response<UserResponse>

    @POST("adduser")
    suspend fun addUser(@Body addUser: AddUser): Response<UserResponse>

    @POST("getorganizationusers")
    suspend fun getUsers(@Body getUsers: GetUsers): Response<UserResponse>

    @POST("getprojectusers")
    suspend fun getProjectUsers(@Body getUsers: GetUsers): Response<UserResponse>

    @POST("login")
    suspend fun login(@Body loginModel: LoginModel): Response<AuthResponse>

    @POST("signup")
    suspend fun login(@Body signUpModel: SignUpModel): Response<AuthResponse>

    @POST("addplan")
    suspend fun addPlan(@Body addPlan: AddPlan): Response<PlanResponse>

    @POST("getplans")
    suspend fun getPlans(@Body getPlans: GetPlans): Response<PlanResponse>

    @POST("addtask")
    suspend fun addTask(@Body addTask: AddTask): Response<GetTaskResponse>

    @POST("gettask")
    suspend fun getTasks(@Body getTasks: GetTasks): Response<GetTaskResponse>

    @POST("gettaskactivities")
    suspend fun getTaskActivites(@Body getTaskActivities: GetTaskActivities): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateTaskActivity(@Body updateTaskActivity: UpdateTaskActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateTaskPipeActivity(@Body updatePipelineActivity: UpdatePipelineActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateHKActvity(@Body updateTaskActivity: UpdateHouseKeepingActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateHSCActvity(@Body updateHSCActvity: UpdateHscActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateMHActivity(@Body updateMHActivity: UpdateMHActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateRoadRestoreActivity(@Body updateTaskActivity: UpdateRoadRestorationActivity): Response<GetTaskActivityResponse>

/*    @POST("updatetaskactivity")
    suspend fun updateTaskActivity(@Body updateTaskActivity: UpdateTaskActivity): Response<GetTaskActivityResponse>

    @POST("updatetaskactivity")
    suspend fun updateTaskActivity(@Body updateTaskActivity: UpdateTaskActivity): Response<GetTaskActivityResponse>*/

    @POST("getactivity")
    suspend fun getActivity(@Body getActivity: GetActivity): Response<GetTaskActivityResponse>

    @POST("assignactivity")
    suspend fun assginTaskActivity(@Body assignTaskActivityModel: AssignTaskActivityModel): Response<GetTaskActivityResponse>

    @POST("getassignedtome")
    suspend fun getAssignedTasksToMe(@Body getTasksAssignedToMe: GetTasksAssignedToMe): Response<GetAssignedTasksResposne>

    @Headers("Content-Type: application/json")
    @POST("getuserassignedtasks")
    suspend fun getAssignedTasks(@Body getTasksAssignedToMe: GetTasksAssignedToMe): Response<GetAssignedTasksResposne>

    @POST("fetchmaterials")
    suspend fun getMaterials(@Body getMachinesAndMaterialModel: GetMachinesAndMaterialModel): Response<GetMacAndMat>

    @POST("fetchmachines")
    suspend fun getMachines(@Body getMachinesAndMaterialModel: GetMachinesAndMaterialModel): Response<GetMacAndMat>

    @POST("fetchroleusers")
    suspend fun getRoleBasedUsers(@Body getRoleBasedUsers: GetRoledBasedUsers): Response<RolesResponse>

    @POST("reportworkdpr")
    suspend fun submitWorkReport(@Body submitCCwork: SubmitTaskReport): Response<GetAssignedTasksResposne>

    @POST("reportworkdpr")
    suspend fun submitPWorkReport(@Body submitCCwork: SubmitPTaskReport): Response<GetAssignedTasksResposne>

    @POST("reportmachinerydpr")
    suspend fun submitMachinereport(@Body submitMachineReport: SubmitMachineryReport): Response<GetAssignedTasksResposne>

    @POST("reportmaterialdpr")
    suspend fun submitMaterialReport(@Body submitMaterialReport: SubmitMaterialReport): Response<GetAssignedTasksResposne>

    @POST("reportmanpowerdpr")
    suspend fun submitManpowerReport(@Body submitManPowerReport: SubmitManPowerReport): Response<GetAssignedTasksResposne>

    @POST("fetchuserreports")
    suspend fun getUserReports(@Body getReports: GetReports): Response<ReportsResponse>

    @POST("fetchallreports")
    suspend fun getReceivedReports(@Body getReports: GetReports): Response<ReportsResponse>

    @POST("approvereport")
    suspend fun approveReport(@Body approvereport: ApproveReport): Response<ReportsResponse>

    @POST("fetchreport")
    suspend fun fetchReport(@Body fetchreport: FetchReport): Response<FetchReportResponse>

    @POST("fetchdpr")
    suspend fun getDprs(@Body getReports: GetReports): Response<DPRs>

    @POST("fetchdfr")
    suspend fun getDfrs(@Body getReports: GetReports): Response<Dfrs>

    @POST("addinvoice")
    suspend fun addMaterialInvoice(@Body addMaterialInvoice: AddMaterialInvoice): Response<InvoiceResponse>

    @POST("getallinvoices")
    suspend fun getMaterialInvoices(@Body getAllMachineInvoices: GetAllMachineInvoices): Response<GetAllInvoices>

    @POST("getinvoice")
    suspend fun getMaterialInvocie(@Body getSingleInvoice: GetSingleInvoice): Response<GetSingleInvoiceResponse>

    @POST("addmachineinvoice")
    suspend fun addMachineInvoice(@Body addMachineInvoice: AddMachineInvoice): Response<GetSingleInvoiceResponse>

    @POST("getallmachineinvoices")
    suspend fun getMachineInvoices(@Body getAllMachineInvoices: GetAllMachineInvoices): Response<GetAllInvoices>

    @POST("getmachineinvoice")
    suspend fun getMachineInvoice(@Body getSingleInvoice: GetSingleInvoice): Response<GetMachineInvoiceResp>

    @POST("dispatchmaterial")
    suspend fun dispatchMaterial(@Body dispatchMaterial: DispatchMaterial): Response<StoreResponse>

    @POST("fetchmaterialdispatchedbyme")
    suspend fun fetchMaterialsDispatchedByMe(@Body fmatdispatchedByMe: GetMacAndMatDispbyMe): Response<MaterialDispatches>

    @POST("fetchmaterialdispatchedtome")
    suspend fun fetchMaterialsDispatchedToMe(@Body fmdispatchedToMe: GetMacAndMatDispbyMe): Response<MaterialDispatches>

    @POST("fetchmaterialdispatched")
    suspend fun fetchMaterialDispatched(@Body fetchMaterialDispatched: FetchMaterialDispatched): Response<FetchMaterialDispatchedResp>

    @POST("approvemachinerydispatch")
    suspend fun approveMachineryDispatch(@Body fetchMachineryDispatched: FetchMachineryDispatched): Response<StoreResponse>

    @POST("dispatchmachinery")
    suspend fun dispatchMachinery(@Body dispatchMachinery: DispatchMachinery): Response<StoreResponse>


    @POST("fetchmachinerysassigned")
    suspend fun getAssignedMachinery(@Body fetchAssignedMachAndMates: FetchAssignedMachAndMates): Response<GetListOfAssignedMachandMates>

    @POST("fetchmaterialsassigned")
    suspend fun getAssignedMaterials(@Body fetchAssignedMachAndMates: FetchAssignedMachAndMates): Response<GetListOfAssignedMachandMates>

    @POST("fetchstoremachinerydispatches")
    suspend fun fetchStoreMachineryDispatches(@Body fetchStoreMaterialDispatches: FetchStoreMaterialDispatches): Response<GetListOfStoreMachineryDispatches>

    @POST("fetchstorematerialdispatches")
    suspend fun fetchStoreMaterialDispatches(@Body fetchStoreMaterialDispatches: FetchStoreMaterialDispatches): Response<GetListOfStoreMachineryDispatches>


    @POST("fetchmachinerydispatchedbyme")
    suspend fun fetchMachineryDispatchedByMe(@Body fmdispatchedByMe: GetMacAndMatDispbyMe): Response<MachineryDispatches>

    @POST("fetchmachinerydispatchedtome")
    suspend fun fetchMachineryDispatchedToMe(@Body fmdispatchedByMe: GetMacAndMatDispbyMe): Response<MachineryDispatches>

    @POST("fetchmachinerydispatched")
    suspend fun fetchMachineryDispatched(@Body fetchMachineryDispatched: FetchMachineryDispatched): Response<FetchMachineryDispatchedResp>

    @POST("approvematerialdispatch")
    suspend fun approveMaterialDispatch(@Body fetchMaterialDispatched: FetchMaterialDispatched): Response<StoreResponse>

    @POST("forgotpassword")
    suspend fun forgotPassword(@Body forgotPassword: ForgotPassword): Response<StoreResponse>

    @POST("resetpassword")
    suspend fun resetPassword(@Body forgotPassword: ForgotPassword): Response<StoreResponse>

    /* @POST("reportworkdpr")
     suspend fun submitHscWorkreport(@Body submitCCwork: SubmitTaskReport): Response<GetAssignedTasksResposne>

     @POST("reportworkdpr")
     suspend fun submitMHWorkreport(@Body submitCCwork: SubmitTaskReport): Response<GetAssignedTasksResposne>

     @POST("reportworkdpr")
     suspend fun submitRRWorkreport(@Body submitCCwork: SubmitTaskReport): Response<GetAssignedTasksResposne>

     @POST("reportworkdpr")
     suspend fun submitPipeWorkreport(@Body submitCCwork: SubmitTaskReport): Response<GetAssignedTasksResposne>*/

    @POST("logout")
    suspend fun logout(@Body getTasksAssignedToMe: GetTasksAssignedToMe): Response<AuthResponse>


}