package com.example.hopelastrestart1.data.network.responses
import com.example.hopelastrestart1.data.db.entities.Task
import com.example.hopelastrestart1.model.GetAssignedTaskActivitesModel

data class GetTaskResponse (
    val isSuccessful : Boolean?,
    val status_code : Int?,
    val user: String?,
    val taskid: String?,
//    val userRole: String?,
    val message: String?,
    val taskinfo: List<Task>?,
    val assigned_activities: List<GetAssignedTaskActivitesModel>?,
    val assigned_activity: List<GetAssignedTaskActivitesModel>?
 )