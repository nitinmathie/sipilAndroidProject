package com.example.hopelastrestart1.data.db.entities
import androidx.room.Entity

@Entity(primaryKeys = ["project_id"])
data class Project(
    //@PrimaryKey(autoGenerate = false)
    val project_id: Int?,
    val project_name: String?,
    val organization_project_id:Int?,
    val project_type: String?,
    val project_location: String?,
    val project_status: String?
    //val organization_projects : List<String>?,
    //val organization_users: List<String>?
/*
    val created_at:String?,
    val updated_at:String?,
    val created_by:String?,
    val updated_by:String?

 */
)