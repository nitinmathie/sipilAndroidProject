package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class UpdateProfile (
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("first_name") var frst_name: String,
    @SerializedName("last_name") var last_name: String,
    @SerializedName("address_line1") var address_line_1: String,
    @SerializedName("address_line2") var address_line_2: String,
    @SerializedName("pin") var pin: String

)