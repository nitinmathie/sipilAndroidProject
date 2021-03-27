package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class AddOrgModel (
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("organization_email") var org_email: String,
    @SerializedName("organization_address") var org_address: String
    )
