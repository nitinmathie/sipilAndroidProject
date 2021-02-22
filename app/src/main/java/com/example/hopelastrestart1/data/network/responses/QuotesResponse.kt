package com.example.hopelastrestart1.data.network.responses

import com.example.hopelastrestart1.data.db.entities.Quote


data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)

