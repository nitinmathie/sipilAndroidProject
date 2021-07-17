package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class UserAdded(
    @PrimaryKey(autoGenerate = false)
    val user_id: Int?,
    val user_email: String?,
    val user_role: String,
    val username: String?,
    val organization_id: Int?,
    val project_id: String,
    val role: String?,
/*    val created_at:String?,
    val updated_at:String?,
    val created_by:String?,
    val updated_by:String?
 */
)

data class UserList
    (
    val user_email: String?,
/*    val created_at:String?,
    val updated_at:String?,
    val created_by:String?,
    val updated_by:String?
 */
)