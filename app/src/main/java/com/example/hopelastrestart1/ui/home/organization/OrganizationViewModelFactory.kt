package com.example.hopelastrestart1.ui.home.organization
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.data.repository.OrganizationRepository


@Suppress("UNCHECKED_CAST")
class OrganizationViewModelFactory(
    private val repository: OrganizationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrganizationViewModel(repository) as T
    }
}