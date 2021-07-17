package com.example.hopelastrestart1.model

import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Work(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: String,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)

data class CCWork(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: Boolean,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)

data class MHWork(
    @SerializedName("mh_task_id") var mh_task_id: String,
    @SerializedName("manhole_activity_name") var manhole_activity_name: String,
    @SerializedName("excavation") var excavation: String,
    @SerializedName("bedding") var bedding: String,
    @SerializedName("sand_bedding") var sand_bedding: Boolean,
    @SerializedName("pcc") var pcc: Boolean,
    @SerializedName("base_erection") var base_erection: Boolean,
    @SerializedName("pipe_jointing") var pipe_jointing: String,
    @SerializedName("haunching") var haunching: Boolean,
    @SerializedName("raiser_erection") var raiser_erection: String,
    @SerializedName("cone_erection") var cone_erection: String,
    @SerializedName("fix_UPVC_MH") var fix_UPVC_MH: String,
    @SerializedName("joint_holes_sealing") var joint_holes_sealing: String,
    @SerializedName("open_holes_sealing") var open_holes_sealing: String,
    @SerializedName("back_filling") var back_filling: String,
    @SerializedName("consolidation") var consolidation: Boolean,
    @SerializedName("removal_excess_soil") var removal_excess_soil: String,
)
data class PipeWork(
    @SerializedName("pipe_task_id") var pipe_task_id: String,
    @SerializedName("pipeline_activity_name") var pipeline_activity_name: String,
    @SerializedName("trenching_pipeline") var trenching_pipeline: String,
    @SerializedName("bedding") var bedding: String,
    @SerializedName("laying") var laying: String,
    @SerializedName("back_filling") var back_filling: String,
    @SerializedName("bottom_trench") var bottom_trench: String,
    @SerializedName("midtrench") var midtrench: String,
    @SerializedName("toptrench") var toptrench: String,
    @SerializedName("consolidation") var consolidation: String,
    @SerializedName("removal_excess_soil") var removal_excess_soil: Boolean
)
data class HkWork(
    @SerializedName("hk_task_id") var hk_task_id: String,
    @SerializedName("housekeeping_activity_name") var housekeeping_activity_name: String,
    @SerializedName("hk_activity") var hk_activity: String
)
data class HscWork(
    @SerializedName("hsc_task_id") var hsc_task_id: String,
    @SerializedName("hsc_activity_name") var hsc_activity_name: String,
    @SerializedName("excavation_for_IC") var excavation_for_IC: String,
    @SerializedName("PCC_below_IC") var PCC_below_IC: String,
    @SerializedName("erection_IC") var erection_IC: String,
    @SerializedName("upvc_ic_jointing") var upvc_ic_jointing: String,
    @SerializedName("provisions") var provisions: String,
    @SerializedName("removal_of_excess_soil") var removal_of_excess_soil: Boolean,
    @SerializedName("dust_filling") var dust_filling: String
)
data class RRWork(

    @SerializedName("rr_task_id") var rr_task_id: String,
    @SerializedName("restoration_activity_name") var restoration_activity_name: String,
    @SerializedName("trenching_length_pipe") var trenching_length_pipe: String,
    @SerializedName("trenching_width_pipe") var trenching_width_pipe: String,
    @SerializedName("fill_with_dust_pipe") var fill_with_dust_pipe: Boolean,
    @SerializedName("trenching_length_pipe_pcc") var trenching_length_pipe_pcc: String,
    @SerializedName("trenching_width_pipe_pcc") var trenching_width_pipe_pcc: String,
    @SerializedName("trenching_depth_pipe_pcc") var trenching_depth_pipe_pcc: String,
    @SerializedName("trenching_length_pipe_vcc") var trenching_length_pipe_vcc: String,
    @SerializedName("trenching_width_pipe_vcc") var trenching_width_pipe_vcc: String,
    @SerializedName("trenching_depth_pipe_vcc") var trenching_depth_pipe_vcc: String,
    @SerializedName("trenching_length_UPVC") var trenching_length_UPVC: String,
    @SerializedName("trenching_width_UPVC") var trenching_width_UPVC: String,
    @SerializedName("fill_with_dust_UPVC") var fill_with_dust_UPVC: Boolean,
    @SerializedName("trenching_length_UPVC_vcc") var trenching_length_UPVC_vcc: String,
    @SerializedName("trenching_width_UPVC_vcc") var trenching_width_UPVC_vcc: String,
    @SerializedName("trenching_depth_UPVC_vcc") var trenching_depth_UPVC_vcc: String,
)

/*data class CCWork(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: String,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)

data class CCWork(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: String,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)

data class CCWork(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: String,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)

data class CCWork(
    @SerializedName("cc_task_id") var cc_task_id: String,
    @SerializedName("ccbreaking_activity_name") var ccbreaking_activity_name: String,
    @SerializedName("ccb_pipeline_trench_500_status") var ccb_pipeline_trench_500_status: String,
    @SerializedName("ccb_upvc_350") var ccb_upvc_350: String,
    @SerializedName("ccb_IC_500") var ccb_IC_500: String,
    @SerializedName("ccb_mharea_status") var ccb_mharea_status: Boolean,
)*/


data class SkilledWork(
    @SerializedName("skilled") var skilled: UpdateTaskActivity,
)

data class UpdateMH(
    @SerializedName("skilled") var skilled: UpdateMHActivity,
)

data class UpdatedPL(
    @SerializedName("skilled") var skilled: UpdatePipelineActivity,
)

data class UpdateHsc(
    @SerializedName("skilled") var skilled: UpdateHscActivity,
)

data class UpdateHK(
    @SerializedName("skilled") var skilled: UpdateHouseKeepingActivity,
)

data class UpdateRR(
    @SerializedName("skilled") var skilled: UpdateRoadRestorationActivity,
)


