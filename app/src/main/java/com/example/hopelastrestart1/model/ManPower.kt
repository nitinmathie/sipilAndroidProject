package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class ManPower(
    @SerializedName("skilled") var skilled: String,
    @SerializedName("unskilled") var unskilled: String,
)