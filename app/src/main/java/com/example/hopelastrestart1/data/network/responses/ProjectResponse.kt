package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Project

data class ProjectResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val projects: List<Project>?
)