package com.example.hopelastrestart1.model

import com.example.hopelastrestart1.data.db.entities.Organization

data class Project (
    val project_name : String?,
    val project_id : Int?,
    //val message: String?,
    val organization_project_id: Int?
)


