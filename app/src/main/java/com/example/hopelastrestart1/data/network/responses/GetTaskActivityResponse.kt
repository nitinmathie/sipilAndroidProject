package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.*

data class GetTaskActivityResponse (
    val isSuccessful : Boolean?,
    val user: String?,
    //val userRole: String?,
    val message: String?,
    //val task_id: String?,
    val task_activities: List<Activit>
)
