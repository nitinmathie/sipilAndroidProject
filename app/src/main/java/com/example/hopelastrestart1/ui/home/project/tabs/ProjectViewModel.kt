package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.repository.ProjectRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectViewModel(private val repository: ProjectRepository): ViewModel() {
    val projects by lazyDeferred {
        repository.getProjects("", organization_name = "")
    }
    suspend fun projects1(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) { repository.getProjects(username, organization_name) }
    suspend fun userOrganizationProjects(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) { repository.userOrganizationProjects(username, organization_name) }
    //adding an project change input
    suspend fun addProject(
        projName: String,
        orgName: String,
        projType: String,
        projLocation: String,
        projDescription :String,
        projUser: String
    ) = withContext(Dispatchers.IO) { repository.addProject(projName, orgName, projType, projLocation, projDescription, projUser) }

}