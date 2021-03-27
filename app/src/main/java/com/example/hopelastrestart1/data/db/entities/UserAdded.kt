package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAdded(
    @PrimaryKey(autoGenerate = false)
    val user_id: Int?,
    val user_email: String?,
    val user_role: String?,
    val username: String?,
    val organization_id: Int?,
    val project_id: Int?,
    val role: String?,
/*    val created_at:String?,
    val updated_at:String?,
    val created_by:String?,
    val updated_by:String?
 */
)