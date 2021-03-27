package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?,
    val status_code: String?,
    val email: String?,
    val token: String?,
    val firstname: String?,
    val logout: String?
)