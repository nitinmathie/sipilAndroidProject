package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.AssignedActivityNow
import com.example.hopelastrestart1.data.db.entities.AssignedByActivity
import com.example.hopelastrestart1.data.db.entities.AssignedToActivity
import com.example.hopelastrestart1.data.db.entities.AssignedToMeEntity
import com.example.hopelastrestart1.model.AssignTaskActivityModel
import com.example.hopelastrestart1.model.GetAssignedTaskActivitesModel

//data class AssignedActivityResponse (
//    val isSuccessful : Boolean?,
//    val user: String?,
//    val userRole: String?,
//    val message: String?,
//    val assigned_activity: AssignedActivity?
//)

data class AssignedActivitiesResponse(
    val assigned_activity: List<AssignedByActivity>,
    val isSuccessful: Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,

    )

data class ReceivedActivitiesResponse(
    val isSuccessful: Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val assigned_activity: List<AssignedToActivity>
)

data class onActivityAssigned(
    val assigned_activity: AssignedActivityNow,
    val isSuccessful: Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,

    )

data class AssignedToMe(
    val assigned__to_me: List<AssignedToMeEntity>,
    val isSuccessful: Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,

    )

data class GetAssignedTasksResposne
    (
    val status_code: String,
    val user: String?,
    val assigned_activities: List<GetAssignedTaskActivitesModel>,
    val assigned_activity: List<GetAssignedTaskActivitesModel>
)