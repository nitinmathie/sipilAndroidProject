package com.example.hopelastrestart1.data.db.entities
import androidx.room.Entity

@Entity(primaryKeys = ["plan_id"])
data class Plan(
    //@PrimaryKey(autoGenerate = false)
    val project_id: Int?,
    val plan_id: Int?,
    val plan_name: String?,
    val plan_description: String?,
    val plan_template: String?,
    val plan_project_id: Int?,
    val plan_organization_id: Int?,
    val plan_created_by: Int?,
    val plan_updated_by: Int?,

)