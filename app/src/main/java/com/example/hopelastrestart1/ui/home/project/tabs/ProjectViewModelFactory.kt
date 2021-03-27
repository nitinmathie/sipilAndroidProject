package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hopelastrestart1.data.repository.ProjectRepository


@Suppress("UNCHECKED_CAST")
class ProjectViewModelFactory(
    private val repository: ProjectRepository
) : ViewModelProvider.NewInstanceFactory() {

   /* override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //return ProjectViewModel(repository) as T
    }*/
}
