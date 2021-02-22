package com.example.hopelastrestart1.ui.home.plen.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.data.repository.PlanRepository


@Suppress("UNCHECKED_CAST")
class PlanViewModelFactory(
    private val repository: PlanRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlanViewModel(repository) as T
    }
}