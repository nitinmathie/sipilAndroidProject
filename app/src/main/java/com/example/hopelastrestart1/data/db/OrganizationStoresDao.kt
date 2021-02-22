package com.example.hopelastrestart1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hopelastrestart1.data.db.entities.Indent
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.data.db.entities.Store

@Dao
interface Organization_Store_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllOrganizationStores(stores: List<Store>)
    @Query("SELECT * FROM Store")
    fun getStores(): LiveData<List<Store>>
    @Query("DELETE FROM Store")
    fun deleteAll()
}

@Dao
interface Invoice_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllInvoices(invoices: List<Invoice>)
    @Query("SELECT * FROM Invoice")
    fun getInvoices(): LiveData<List<Invoice>>
    @Query("DELETE FROM Invoice")
    fun deleteAll()
}
@Dao
interface Indent_Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllIndents(invoices: List<Indent>)
    @Query("SELECT * FROM Indent")
    fun getIndents(): LiveData<List<Indent>>
    @Query("DELETE FROM Indent")
    fun deleteAll()
}

