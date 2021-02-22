package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.hopelastrestart1.data.db.entities.Plan

@Dao
interface project_plans_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllProjectPlans(plans: List<Plan>)
    @Query("SELECT * FROM `Plan`")
    fun getPlans(): LiveData<List<Plan>>
    @Query("DELETE FROM `Plan`")
    fun deleteAll()
}