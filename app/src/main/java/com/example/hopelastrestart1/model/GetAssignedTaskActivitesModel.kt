package com.example.hopelastrestart1.model

import com.google.gson.JsonObject
import org.json.JSONObject

class GetAssignedTaskActivitesModel
    (
    val assigned_activity_id: String?,
    val activity_task_id: String?,
    val activity_name: String?,
    val activity_type: String?,
    val sub_activity_name: String?,
    val assigned_by: String?,
    val assigned_on: String?,
    val assigned_to: String?,
    val estimated_timeline: String?,
    val approved_on: String?,
    val activity_status: String?,
    val work: Skilled?,
    val material: LinkedHashMap<String,String>?,
    val machinery: LinkedHashMap<String,String>?,
    //   val material: nameValuePairs?,
    // val machinery: nameValuePairs?,
    val manpower: ManPower?

)

data class Skilled(
    val skilled: UpdateTaskActivity?
)

data class namevpar(
    val nameValuePairs: LinkedHashMap<String, String>?
)


data class WorkTask(
    val nameValuePairs: WorkNameValuePairs
)

data class WorkNameValuePairs(
    val skilled: SkilledTask
)

data class SkilledTask(
    val nameValuePairs: UpdateTaskActivity
)

