package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

data class GetReports(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String

)

data class GetDPRs(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("task_name") var task_name: String

)

data class Report(
    val report_type: String?,
    val dpr_id: Int?,
    val assigned_activity_id: String?,
    val activity_name: String?

)

data class DPRs(
    val Pipelaying: Units,
    val ManholeErection: Units,
    val RoadRestoration: Units,
    val UPVC_laying: Units,
    val IC: Units,
    val FlowTest: Units,
    val Benching: Units
)

data class Units(
    val quantity: String,
    val unit: String,
    val price: String,
    val name: String
)

data class ReportsResponse(
    val material_reports: List<Report>?,
    val reports_machinery: List<Report>?,
    val reports_work: List<Report>?,
    val reports_manpower: List<Report>?,
    val status_code: String?,
    val isSuccessful: Boolean?

)

data class Dfrs(
    val x: List<X>
)

data class X(
    val Date: String,
    val Pipelaying: Benching,
    val ManholeErection: Benching,
    val RoadRestoration: Benching,
    val UPVC_laying: Benching,
    val FlowTest: Benching,
    val IC: Benching,
    val Benching: Benching
)

data class Benching(
    val quantity: String
)
data class ApproveReport(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    //  @SerializedName("task_name") var task_name: String,
    @SerializedName("report_type") var report_type: String,
    @SerializedName("dpr_id") var dpr_id: Int?,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String?,
    @SerializedName("activity_name") var activity_name: String?,

    )

data class FetchReport(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var org_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("dpr_id") var dpr_id: Int?,
    @SerializedName("assigned_activity_id") var assigned_activity_id: String?,
    @SerializedName("activity_name") var activity_name: String?,
    @SerializedName("report_type") var report_type: String?,

    )

/*data class FetchReportResponse(
    val project_name: String,
    val status: String,
    val reported_on: String,
)*/


data class FetchReportResponse(
    @SerializedName("report_type")
    val reportType: String,

    val activity: Activity,

    @SerializedName("status_code")
    val statusCode: Long
)

data class Activity(
    @SerializedName("dpr_id")
    val dprID: Long,

    @SerializedName("work_progress")
    val workProgress: WorkProgress,

    @SerializedName("report_type")
    val reportType: String,

    @SerializedName("project_name")
    val projectName: String,

    @SerializedName("organization_name")
    val organizationName: String,

    @SerializedName("plan_name")
    val planName: String,

    @SerializedName("assigned_activity_id")
    val assignedActivityID: String,

    @SerializedName("sub_activity_name")
    val subActivityName: String,

    @SerializedName("activity_name")
    val activityName: String,

    @SerializedName("activity_type")
    val activityType: String,

    @SerializedName("reported_by")
    val reportedBy: String,

    @SerializedName("reported_on")
    val reportedOn: String,

    val status: String,

    @SerializedName("approved_on")
    val approvedOn: String,

    @SerializedName("approved_by")
    val approvedBy: String
)

data class WorkProgress(
    val skilled: LinkedHashMap<String, String>
)

data class SkilledFetchReport(
    val nameValuePairs: NameValuePairsFReprt
)

data class NameValuePairsFReprt(
    @SerializedName("cc_task_id")
    val ccTaskID: String,

    @SerializedName("ccb_IC_500")
    val ccbIC500: String,

    @SerializedName("ccb_mharea_status")
    val ccbMhareaStatus: Boolean,

    @SerializedName("ccb_pipeline_trench_500_status")
    val ccbPipelineTrench500_Status: String,

    @SerializedName("ccb_upvc_350")
    val ccbUpvc350: String,

    @SerializedName("ccbreaking_activity_name")
    val ccbreakingActivityName: String
)

