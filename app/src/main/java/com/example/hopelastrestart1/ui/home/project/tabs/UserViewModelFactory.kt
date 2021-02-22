package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.data.repository.OrganizationUserRepository

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val repository: OrganizationUserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}