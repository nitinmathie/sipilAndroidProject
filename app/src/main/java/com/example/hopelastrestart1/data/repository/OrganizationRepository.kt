package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.OrganizationResponse
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrganizationRepository(private val api: MyApi,
                             private val db: AppDatabase
) : SafeApiRequest() {
private val organizations = MutableLiveData<List<Organization>>()
    init{
        organizations.observeForever{
            saveOrganizations(it)
        }    }
    private fun saveOrganizations(orgs: List<Organization>){
        Coroutines.io{
            db.getOrganizationDao().saveAllUserOrganizations(orgs)
        }
    }
    private suspend fun fetchOrganizations(email: String){
        if(isFetchNeeded()){
            //response =  apiRequest { api.userOrganizations(email) }
            val response = apiRequest{api.userOrganizations(email)}
            organizations.postValue(response.organizations)
        }
    }
    suspend fun getOrganizations(email: String):LiveData<List<Organization>>{
        return withContext(Dispatchers.IO){
            fetchOrganizations(email)
            db.getOrganizationDao().getOrganizations()
        }
    }
    private fun isFetchNeeded(): Boolean{
        return true
    }
    suspend fun userOrganizations(email: String): OrganizationResponse {
        return apiRequest { api.userOrganizations(email) }
    }
    //add organization
    suspend fun addOrganization(orgName: String, orgEmail: String, orgLocation: String, orgUser:String): OrganizationResponse {
        return apiRequest { api.addOrganization(orgName, orgEmail,orgLocation, orgUser) }
    }
}