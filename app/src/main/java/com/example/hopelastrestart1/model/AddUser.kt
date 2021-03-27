package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class AddUser(
    @SerializedName("project_name") var project_name: String,
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("add_user_email") var add_user_email: String
)