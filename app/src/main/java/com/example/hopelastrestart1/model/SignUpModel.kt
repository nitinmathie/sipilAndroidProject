package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class SignUpModel(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("password") var password: String,
    @SerializedName("first_name") var first_name: String,
    @SerializedName("last_name") var last_name: String
)


