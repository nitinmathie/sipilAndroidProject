package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class AddTask(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var taskName: String,
    @SerializedName("task_startnode") var taskStNode: String,
    @SerializedName("task_endnode") var taskEnNode: String,
    @SerializedName("task_ref_street_name") var taskRefStreetName: String

)