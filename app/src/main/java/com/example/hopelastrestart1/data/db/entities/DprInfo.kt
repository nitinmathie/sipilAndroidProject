package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DprInfo(
    @PrimaryKey(autoGenerate = false)
    val username: String?,
    val organization_name: String?,
    val project_name: String?,
    val task_id: Int?,
    val dpr_id: Int?
    )