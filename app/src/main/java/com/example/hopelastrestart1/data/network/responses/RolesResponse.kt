package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.model.GetRoledUsers
import com.example.hopelastrestart1.model.Roles

class RolesResponse
    (
    val status_code: String?,
    val user_email: String?,
    val users: List<GetRoledUsers>?,
    val organizations_projects_roles: List<Roles>?

)