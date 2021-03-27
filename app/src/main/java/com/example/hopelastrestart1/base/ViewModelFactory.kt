package com.example.hopelastrestart1.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.ui.home.project.tabs.ProjectViewModel
import com.example.hopelastrestart1.viewmodel.*
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            return ProjectViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(PlanViewModel::class.java)) {
            return PlanViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(apiService) as T
        }
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            return StoreViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}