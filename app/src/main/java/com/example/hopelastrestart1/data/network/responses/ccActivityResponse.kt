package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.*

data class ccActivityResponse (
    val task_id : Int?,
    val activity: CcAct?
)

data class ActivityResponse (
    val task_type : String?,
    val activity: CcAct?
)

data class pipeActivityResponse (

    val task_id : Int?,
    val activity: PipeAct?
)
data class mhActivityResponse (
    val task_id : Int?,
    val activity: MhAct?
)
data class hscActivityResponse (
    val task_id : Int?,
    val activity: HscAct?
)
data class hkActivityResponse (
    val task_id : Int?,
    val activity: HkAct?
)