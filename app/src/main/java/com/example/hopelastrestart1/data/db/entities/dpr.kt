package com.example.hopelastrestart1.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
//ENSRURE the entites are correct
@Entity
data class DPR(
    @PrimaryKey(autoGenerate = false)
    val assigned_activity_id: Int?,
    val activity_type_id:String?,
    val assigned_by:String?,
    val assigned_on:String?,
    val assigned_to:String?,
    val estimated_timeline:String?,
    val skilled_man_power:String?,
    val skilled_man_hours: Int?,
    val unskilled_man_power: String?,
    val unskilled_man_hours:String?,
    val jcb_quantity:String?,
    val jcb_hours:String?,
    val tractor_quantity:String?,
    val tracktor_hours:String?,
    val hydra_quantity:String?,
    val hydra_hours: Int?,
    val water_tanker_quantity: String?,
    val water_tanker:String?,
    val tractor_compressor_quantity:String?,
    val tractor_compressor_hours:String?,
    val other_machine_quantity:String?,
    val other_machine_hours:String?,
    val material_1: String?,
    val material_1_quantity:String?,
    val material_2:String?,
    val material_2_quantity:String?,
    val material_3:String?,
    val material_3_quantity:String?,
    val assign_task_id:String?,
)