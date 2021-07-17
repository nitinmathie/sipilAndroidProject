package com.example.hopelastrestart1.data.network.responses
import com.example.hopelastrestart1.data.db.entities.Organization

data class OrganizationResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    val user: String?,
    val error: String?,
    val userRole: String?,
    val status_code: String?,
    val message: String?,
     val organizations: List<Organization>?
)
