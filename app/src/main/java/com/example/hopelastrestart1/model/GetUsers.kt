package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetUsers (
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var token: String,
    @SerializedName("organization_name") var organization_name: String,
 //   @SerializedName("project_name") var project_name: String
    )