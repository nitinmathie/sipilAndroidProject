package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.model.Project


data class ProjectResponse(
    val isSuccessful: Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val projects: List<Project>?,
    val status_code: Int?,
    val other: Int?,
    //val message: String?,
    val project: Project?,
)