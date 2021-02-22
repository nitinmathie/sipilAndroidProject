package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Plan

data class PlanResponse (
    val isSuccessful : Boolean?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val plans: List<Plan>?
)