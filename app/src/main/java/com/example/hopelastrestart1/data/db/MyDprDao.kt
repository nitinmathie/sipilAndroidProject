package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.DprInfo
import com.example.hopelastrestart1.data.db.entities.Task

@Dao
interface My_Dpr_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMyDprs(tasks: List<DprInfo>)
    @Query("SELECT * FROM DprInfo")
    fun getAllMyDprs(): LiveData<List<DprInfo>>
    @Query("DELETE FROM Task")
    fun deleteAll()
}