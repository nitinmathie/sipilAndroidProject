package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.UserResponse
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrganizationUserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val users = MutableLiveData<List<UserAdded>>()

    init {
        users.observeForever {
            saveUsers(it)
        }
    }

    private fun saveUsers(useradded: List<UserAdded>) {
        Coroutines.io {
            db.getUserAddedDao().deleteAll()
            db.getUserAddedDao().saveAllOrganizationUsers(useradded)
        }
    }

    private suspend fun fetchUsers(username: String, organization_name: String) {
        if (isFetchNeeded()) {
            val response = apiRequest { api.organizationUsers(username, organization_name) }
            // users.postValue(null)
            users.postValue(response.users)
        }
    }

    suspend fun getUsers(username: String, organization_name: String): LiveData<List<UserAdded>> {
        return withContext(Dispatchers.IO) {
            fetchUsers(username, organization_name)
            db.getUserAddedDao().getUsers()
        }
    }

    fun getusers_by_role(): LiveData<List<UserAdded>> {
        return db.getUserAddedDao().getUsers()

    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    suspend fun OrganizationUsers(username: String, organization_name: String): UserResponse {
        return apiRequest { api.organizationUsers(username, organization_name) }
    }

    //add organization
    suspend fun addUser(
        username: String,
        organization: String,
        name: String,
        email: String,
        project: String,
        role: String
    ): UserResponse {
        return apiRequest { api.addUser(username, organization, name, email, project, role) }
    }
}