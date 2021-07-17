package com.example.hopelastrestart1.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class SubmitTaskReport(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("activity_type") var activity_type: String,
    @SerializedName("work_progress") var work_progress: TaskWork?,
    @SerializedName("activity_name") var activity_name: String,


    )

class SubmitPTaskReport(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("activity_type") var activity_type: String,
    @SerializedName("work_progress") var work_progress: TaskWork?,
    @SerializedName("activity_name") var activity_name: String,


    )

data class Skill(
    @SerializedName("skilled") var skilled: JSONObject,
)

data class TaskWork(
    @SerializedName("skilled") var skilled: HashMap<String, String>,
)


class SubmitMachineryReport(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("machine_quantities") var machine_quantities: JSONObject,
    @SerializedName("activity_name") var activity_name: String,
)

class SubmitMaterialReport(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("material_quantities") var material_quantities: HashMap<String, String>,
    @SerializedName("activity_name") var activity_name: String,
)

class SubmitManPowerReport(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("manpower_quantities") var reportmanpowerdpr: MP,
    @SerializedName("activity_name") var activity_name: String,

    )

data class MP(
    val skilled: Sk,
    val unskilled: USk
)

data class Sk(
    val count: String,
    val hours: String
)

data class USk(
    val count: String,
    val hours: String
)

class SubmitHKWorkActivityReportModel(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("activity_type") var activity_type: String,
    @SerializedName("material_quantities") var material_quantities: Material,
    @SerializedName("machine_quantities") var machine_quantities: Machine,
    @SerializedName("manpower_quantities") var manpower_quantities: ManpowerNew,
    @SerializedName("work_progress") var work_progress: Work,
    @SerializedName("activity_name") var activity_name: String,


    )

class SubmitPipeWorkActivityReportModel(

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("activity_type") var activity_type: String,
    @SerializedName("work_progress") var work_progress: PipeWork?,
    @SerializedName("activity_name") var activity_name: String,


    )

