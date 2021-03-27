package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.OrganizationResponse
import com.example.hopelastrestart1.model.AddOrgModel
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class OrganizationRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val organizations = MutableLiveData<List<Organization>>()

    init { organizations.observeForever {
            saveOrganizations(it)
        }
    }

    private fun saveOrganizations(orgs: List<Organization>) {
        Coroutines.io {
            db.getOrganizationDao().saveAllUserOrganizations(orgs)
        }
    }

    private suspend fun fetchOrganizations(getOrgModel: GetOrgModel) {
        if (isFetchNeeded()) {
            val response = apiRequest {
                api.userOrganizations(getOrgModel)
            }
            organizations.postValue(response.organizations!!)
        }
    }

    suspend fun getOrganizations(getOrgModel: GetOrgModel): LiveData<List<Organization>> {
        return withContext(Dispatchers.IO) {
            fetchOrganizations(getOrgModel)
            db.getOrganizationDao().getOrganizations()
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    suspend fun userOrganizations(getOrgModel: GetOrgModel): OrganizationResponse {
        return apiRequest { api.userOrganizations(getOrgModel) }
    }

    //add organization
    suspend fun addOrganization(addOrgModel: AddOrgModel): OrganizationResponse {
        return apiRequest {
            api.addOrganization(addOrgModel)
        }
    }


}