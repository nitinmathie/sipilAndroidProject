package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

class GetMachinesAndMaterialModel(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var prjct_name: String,
    @SerializedName("plan_name") var plan_name: String
)

data class GetMachines(
    val name: String?,
    val id: String?

)

data class GetMacAndMat(
    val materials: List<GetMachines>?,
    val machines: List<GetMachines>?,
    val status_code: String?
)

data class MachinesQuantity(
    val machineName: String?,
    val quantity: String?
)
data class MachinesStartAndEndTime(
    val machineName: String?,
    val startTime: String?,
    val endTime:String?
)

