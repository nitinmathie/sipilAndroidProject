package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.UserAdded


@Dao
interface Organization_User_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllOrganizationUsers(projects: List<UserAdded>)
    @Query("SELECT * FROM UserAdded")
    fun getUsers(): LiveData<List<UserAdded>>
    @Query("DELETE FROM UserAdded")
    fun deleteAll()
}