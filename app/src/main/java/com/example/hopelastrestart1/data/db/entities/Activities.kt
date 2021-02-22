package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//ENSRURE the entites are correct
@Entity
data class CcAct(
    @PrimaryKey(autoGenerate = false)
    val ccbreaking_activity_id: Int?,
    val ccbreaking_activity_name:String?,
    val ccb_pipeline_trench_500_status:String?,
    val ccb_mharea_status:String?,
    val ccb_upvc_350:String?,
    val ccb_IC_500:String?,
    val started_on:String?,
    val status:String?,
    val completed_on:String?,
    val cc_task_id: Int?
    )


@Entity
data class PipeAct(
    @PrimaryKey(autoGenerate = false)
    val pipe_task_id: Int?,
    val pipe_activity_id: String?,
    val pipe_activity_name:String?,
    val trenching_pipeline:String?,
    val bedding:String?,
    val laying:String?,
    val pipe_jointing:String?,
    val back_filling:String?,
    val started_on:String?,
    val status:String?,
    val completed_on:String?,
    )
@Entity
data class MhAct(
    @PrimaryKey(autoGenerate = false)
    val mh_task_id: Int?,
    val pipe_activity_id: String?,
    val pipe_activity_name:String?,
    val trenching_pipeline:String?,
    val bedding:String?,
    val laying:String?,
    val pipe_jointing:String?,
    val back_filling:String?,
    val started_on:String?,
    val status:String?,
    val completed_on:String?,
    )
@Entity
data class HscAct(
    @PrimaryKey(autoGenerate = false)
    val hsc_task_id: Int?,
    val task_name: String?,
    val task_startnode:String?,
    val task_endnode:String?,
    val task_assigned_by_id:String?,
    val task_assigned_by_name:String?,
    val task_assigned_to:String?,
    val task_assigned_to_name:String?,

    )
@Entity
data class HkAct(
    @PrimaryKey(autoGenerate = false)
    val hk_task_id: Int?,
    val task_name: String?,
    val task_startnode:String?,
    val task_endnode:String?,
    val task_assigned_by_id:String?,
    val task_assigned_by_name:String?,
    val task_assigned_to:String?,
    val task_assigned_to_name:String?,

    )
@Entity
data class Activit(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val  activity_id: Int?,
    val activity_name: String?,
    val activity_type: String?

)