package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.AssignedByActivity
import com.example.hopelastrestart1.data.db.entities.AssignedToActivity
import com.example.hopelastrestart1.data.db.entities.AssignedToMeEntity


@Dao
interface user_received_task_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPlanTasks(tasks: List<AssignedToActivity>)
    @Query("SELECT * FROM AssignedToActivity")
    fun getTasks(): LiveData<List<AssignedToActivity>>
    @Query("DELETE FROM AssignedToActivity")
    fun deleteAll()
}

@Dao
interface assigned_to_me_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllassignedtome(tasks: List<AssignedToMeEntity>)
    @Query("SELECT * FROM AssignedToMeEntity")
    fun getallassignedtome(): LiveData<List<AssignedToMeEntity>>
    @Query("DELETE FROM AssignedToMeEntity")
    fun deleteAll()
}

@Dao
interface user_assigned_task_activities_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllAssignedActivities(tasks: List<AssignedByActivity>)
    @Query("SELECT * FROM AssignedByActivity")
    fun getAssignedTaskActivities(): LiveData<List<AssignedByActivity>>
    @Query("DELETE FROM AssignedByActivity")
    fun deleteAll()
}