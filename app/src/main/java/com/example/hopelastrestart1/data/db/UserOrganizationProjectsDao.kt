package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.Project

@Dao
interface User_Organization_Project_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUserOrganizationProjects(projects: List<Project>)
    @Query("SELECT * FROM Project")
    fun getProjects():LiveData<List<Project>>
    @Query("DELETE FROM Project")
    fun deleteAll()
}