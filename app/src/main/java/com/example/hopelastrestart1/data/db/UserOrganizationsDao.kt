package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.Organization

@Dao
interface User_Organizations_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUserOrganizations(organizations: List<Organization>)
    @Query("SELECT * FROM Organization")
    fun getOrganizations():LiveData<List<Organization>>
}