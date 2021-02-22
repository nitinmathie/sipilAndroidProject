package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.Task
@Dao
interface plan_tasks_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPlanTasks(tasks: List<Task>)
    @Query("SELECT * FROM Task")
    fun getTasks(): LiveData<List<Task>>
    @Query("DELETE FROM Task")
    fun deleteAll()
}