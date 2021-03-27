package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class AddProjectModel(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var prjct_name: String,
    @SerializedName("project_type") var prjct_type: String,
    @SerializedName("project_location") var prjct_location: String,
    @SerializedName("project_description") var prjct_desc: String
)