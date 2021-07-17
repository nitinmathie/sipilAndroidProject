package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.data.db.entities.UserList
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserResponse(
    //val message: String?,
    //val user: String?,
    val userRole: String?,
    val user_email: String?,
    val first_name: String?,
    val last_name: String?,
    val address_line1: String?,
    val address_line2: String?,
    val pin: String?,
    val message: String?,
    val users: List<UserAdded>?,
    //val users: List<UserList>?,
    val isSuccessful: Boolean?,
    val status_code: Int?,
    val project_id: Int?,
    val organization_id: Int?,
    val role: String?,
    val user_id: Int?,
)

data class UserResp(
    val user_role: String?,
    val user_email: String?
)

