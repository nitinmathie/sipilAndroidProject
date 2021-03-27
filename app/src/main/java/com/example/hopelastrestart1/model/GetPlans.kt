package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetPlans(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var prjct_name: String
)