package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Indent
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.data.db.entities.Store


data class StoreResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val stores: List<Store>?
)

data class InvoiceResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val invoice: List<Invoice>?
)

data class IndentResponse(
    val isSuccessful : Boolean?,
    //val message: String?,
    val user: String?,
    val userRole: String?,
    val message: String?,
    val indent: List<Indent>?
)