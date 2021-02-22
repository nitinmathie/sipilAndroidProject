package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.AssignedToActivity

data class DprListResponse (
    val isSuccessful : Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val dprs: List<AssignedToActivity>?
)