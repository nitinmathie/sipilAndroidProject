package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Organization(
    @PrimaryKey(autoGenerate = false)
    val organization_id: Int?,
    val organization_name: String?,
    val organization_email: String?,
    //val organization_projects : List<String>?,
    //val organization_users: List<String>?
/*
    val created_at:String?,
    val updated_at:String?,
    val created_by:String?,
    val updated_by:String?

 */
)