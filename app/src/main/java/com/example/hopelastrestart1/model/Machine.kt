package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class  Machine(
    @SerializedName("machine_name") var machine_name: String,
    @SerializedName("start_time") var start_time: String,
    @SerializedName("end_time") var end_time: String,
)