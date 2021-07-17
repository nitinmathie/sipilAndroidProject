package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.*

data class GetTaskActivityResponse(
    val isSuccessful: Boolean?,
    val task_id: Int?,
    val user: String?,
    val status_code: String?,
    //val userRole: String?,
    val message: String?,
    val activity: HashMap<String, String>,
    //val task_id: String?,
    val task_activities: List<Activit>
)
