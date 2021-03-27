package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Plan

data class PlanResponse(
    val isSuccessful: Boolean?,
    val status_code: Int?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val organization: Int?,
    val plans: List<Plan>,
    val plan: Plan
)