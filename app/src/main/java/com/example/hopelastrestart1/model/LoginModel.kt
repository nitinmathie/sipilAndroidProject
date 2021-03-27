package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class LoginModel(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("password") var password: String,
)


