package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.Project
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.ProjectResponse
import com.example.hopelastrestart1.model.GetPrjctModel
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {
    private val projects = MutableLiveData<List<Project>>()

    //private val pross = LiveData<List<Project>>()
    init {
        projects.observeForever {
            saveProjects(it)
        }
    }

    private fun saveProjects(orgs: List<Project>) {
        Coroutines.io {
            db.getProjectDao().deleteAll()
            db.getProjectDao().saveAllUserOrganizationProjects(orgs)
        }
    }

    private suspend fun fetchProjects(username: String, organization_name: String) {
        if (isFetchNeeded()) {
            val getResp = apiRequest { api.userOrganizationProjects(username, organization_name) }
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?
            //projects.postValue(getResp.projects!!)
        }
    }

    suspend fun getProjects(username: String, organization_name: String): LiveData<List<Project>> {
        return withContext(Dispatchers.IO) {
            fetchProjects(username, organization_name)
            db.getProjectDao().getProjects()
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    suspend fun userOrganizationProjects(
        username: String,
        organization_name: String
    ): ProjectResponse {
        return apiRequest { api.userOrganizationProjects(username, organization_name) }
    }

    //add project
    suspend fun addProject(addPrjctModel: GetPrjctModel): ProjectResponse {
        return apiRequest { api.addProject(addPrjctModel) }
    }
}