package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.AssignedByActivity
import com.example.hopelastrestart1.data.db.entities.DprInfo

data class ReportedResponse (
    val isSuccessful : Boolean?,
    )

data class MyDprs (
    val isSuccessful : Boolean?,
    val DprInfo: List<DprInfo>
)