package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

data class GetReports(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String

)

data class Report(
    val report_type: String?,
    val dpr_id: Int?,
    val assigned_activity_id: String?,
    val activity_name: String?

)

data class ReportsResponse(
    val material_reports: List<Report>?,
    val reports_machinery: List<Report>?,
    val reports_work: List<Report>?,
    val reports_manpower: List<Report>?,
    val status_code: String?,
    val isSuccessful: Boolean?
)