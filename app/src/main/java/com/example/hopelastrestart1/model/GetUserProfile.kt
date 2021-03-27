package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetUserProfile (

    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("email_of_user") var email_of_user: String

)