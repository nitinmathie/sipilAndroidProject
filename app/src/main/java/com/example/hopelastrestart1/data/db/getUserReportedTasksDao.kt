package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.ReportedTask


@Dao
interface user_reported_task_dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPlanTasks(tasks: List<ReportedTask>)
    @Query("SELECT * FROM ReportedTask")
    fun getReportedTasks(): LiveData<List<ReportedTask>>
    @Query("DELETE FROM ReportedTask")
    fun deleteAll()
}