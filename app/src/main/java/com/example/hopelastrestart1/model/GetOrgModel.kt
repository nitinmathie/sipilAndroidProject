package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetOrgModel(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var token: String,
)