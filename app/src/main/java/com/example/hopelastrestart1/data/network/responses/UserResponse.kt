package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.UserAdded

data class UserResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    //val user: String?,
    val userRole: String?,
    val message: String?,
    val users: List<UserAdded>?
)
