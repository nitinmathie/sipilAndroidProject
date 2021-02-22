package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Store(
    @PrimaryKey(autoGenerate = false)
    val store_id: Int?,
    val store_name: String?,
    val store_location:String?,
    val updated_by:String?,
    val store_organization_id:Int?,
    val store_project_id:String?,
)
@Entity
data class Invoice(
    @PrimaryKey(autoGenerate = false)
    val invoice_id: Int?,
    val date_of_entry: String?,
    val store_organization_id:Int?,
    val store_project_id:String?,
)

@Entity
data class Indent(
    @PrimaryKey(autoGenerate = false)
    val indent_id: Int?,
    val activity_name: String?,
    //2-d array required to store material quantity and name
    val store_organization_id:Int?,
    val store_project_id:String?,
)
