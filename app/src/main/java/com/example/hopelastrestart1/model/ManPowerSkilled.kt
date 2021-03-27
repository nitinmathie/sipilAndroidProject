package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class ManPowerSkilled(

    @SerializedName("skilled")
    var skilled: ManpowerNew,
    @SerializedName("unskilled")
    var unskilled: ManpowerNew
)