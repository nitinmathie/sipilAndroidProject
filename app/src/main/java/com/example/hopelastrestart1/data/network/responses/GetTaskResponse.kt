package com.example.hopelastrestart1.data.network.responses
import com.example.hopelastrestart1.data.db.entities.Task
data class GetTaskResponse (
    val isSuccessful : Boolean?,
    val user: String?,
//    val userRole: String?,
    val message: String?,
    val taskinfo: List<Task>?
)