package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReportedTask(
    @PrimaryKey(autoGenerate = false)
    val task_id: Int?,
    val task_name: String?,
    val task_startnode:String?,
    val task_endnode:String?,
    val task_assigned_by_id:String?,
    val task_assigned_by_name:String?,
    val task_assigned_to:String?,
    val task_assigned_to_name:String?,

    )