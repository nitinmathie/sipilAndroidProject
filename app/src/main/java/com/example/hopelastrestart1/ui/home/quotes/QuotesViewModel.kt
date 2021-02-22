package com.example.hopelastrestart1.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.example.hopelastrestart1.data.repositories.QuotesRepository
import com.example.hopelastrestart1.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
