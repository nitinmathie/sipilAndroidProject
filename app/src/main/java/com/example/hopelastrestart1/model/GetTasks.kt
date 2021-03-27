package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetTasks (
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var userToken: String,
    @SerializedName("organization_name") var orgName: String,
    @SerializedName("project_name") var prjctName: String,
    @SerializedName("plan_name") var planName: String,
)
