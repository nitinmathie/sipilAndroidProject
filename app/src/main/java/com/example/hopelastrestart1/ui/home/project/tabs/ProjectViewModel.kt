package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.model.AddProjectModel
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


class ProjectViewModel(private val apiService: ApiService) : ViewModel() {
    fun getProjects(getPrjctModel: GetPrjctModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getProjects(getPrjctModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun addProject(addProjectModel: AddProjectModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addProject(addProjectModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }


}
/*class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {
    val projects by lazyDeferred {
        repository.getProjects("", organization_name = "")
    }

    suspend fun projects1(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) { repository.getProjects(username, organization_name) }

    suspend fun userOrganizationProjects(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) {
        repository.userOrganizationProjects(
            username,
            organization_name
        )
    }

    //adding an project change input
    suspend fun addProject(
        addPrjctModel: AddPrjctModel
    ) = withContext(Dispatchers.IO) { repository.addProject(addPrjctModel) }

}*/


