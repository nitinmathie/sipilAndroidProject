package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.data.repository.OrganizationStoreRepository

@Suppress("UNCHECKED_CAST")
class StoreViewModelFactory(
    private val repository: OrganizationStoreRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoreViewModel(repository) as T
    }
}