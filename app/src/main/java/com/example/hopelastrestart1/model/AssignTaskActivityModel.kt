package com.example.hopelastrestart1.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class AssignTaskActivityModel
    (
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("assigned_to") var assigned_to: String,
    @SerializedName("organization_name") var organization_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String,
    @SerializedName("activity_task_id") var activity_task_id: String,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String,
    @SerializedName("sub_activity_name") var sub_activity_name: String,
    @SerializedName("estimated_timeline") var estimated_timeline: String,
    @SerializedName("activity_type") var activity_type: String,
    @SerializedName("activity_name") var activity_name: String,
    @SerializedName("work") var work: SkilledWork,
    @SerializedName("material") var material: JSONObject,
    @SerializedName("machinery") var machinery: JSONObject,
    @SerializedName("manpower") var manpower: ManPower,
)


