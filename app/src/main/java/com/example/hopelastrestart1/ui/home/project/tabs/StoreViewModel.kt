package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.repository.OrganizationStoreRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoreViewModel(private val repository: OrganizationStoreRepository): ViewModel() {
    val stores by lazyDeferred {
        repository.getStores("", "")
    }
    suspend fun stores1(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) { repository.getStores(username, organization_name) }
    suspend fun OrganizationStores(
        username: String, organization_name: String
    ) = withContext(Dispatchers.IO) { repository.OrganizationStores(username, organization_name) }
    //adding an project change input
    suspend fun addStore(
        username: String, organization_name: String,project: String, storeName: String,storeType: String, storeLocation: String,storeDescription: String
    ) = withContext(Dispatchers.IO) { repository.addStore(username, organization_name,project, storeName,storeType, storeLocation,storeDescription ) }
    suspend fun getStock(
        username: String, organization_name: String,project_name: String, store_name: String
    ) = withContext(Dispatchers.IO) { repository.getStock(username, organization_name, project_name, store_name) }
    suspend fun getAllAddedInvoices(
        username: String, organization_name: String,project_name: String, store_name: String
    ) = withContext(Dispatchers.IO) { repository.getAllAddedInvoices(username, organization_name, project_name, store_name) }
    suspend fun getAllIndents(
        username: String, organization_name: String,project_name: String, store_name: String
    ) = withContext(Dispatchers.IO) { repository.getAllAddedIndents(username, organization_name, project_name, store_name) }


}