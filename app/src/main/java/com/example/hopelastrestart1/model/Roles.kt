package com.example.hopelastrestart1.model

import com.google.gson.annotations.SerializedName

data class Roles(
    val organization_name: String?,
    val project_name: String?,
    val role: String?
)

data class UpdateUserRoles(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var organization_name: String,
    @SerializedName("update_user_email") var update_user_email: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("user_role") var user_role: String
)

data class GetRoledBasedUsers(
    @SerializedName("user_email") var user_email: String,
    @SerializedName("user_token") var user_token: String,
    @SerializedName("organization_name") var organization_name: String,
    @SerializedName("project_name") var project_name: String,
    @SerializedName("plan_name") var plan_name: String,
    @SerializedName("role") var user_role: String
)

data class GetRoledUsers(
    val user_email: String?,
    val user_firstname: String?,
    val user_id: String?
)