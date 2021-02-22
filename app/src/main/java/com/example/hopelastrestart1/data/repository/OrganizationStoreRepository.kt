package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.Indent
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.data.db.entities.Store
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.StoreResponse
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrganizationStoreRepository(private val api: MyApi,
                                  private val db: AppDatabase
) : SafeApiRequest(){
    private val stores = MutableLiveData<List<Store>>()
    private val invoices = MutableLiveData<List<Invoice>>()
    private val indents = MutableLiveData<List<Indent>>()
    init{
        stores.observeForever{

            saveStores(it)
        }    }
    init{
        invoices.observeForever{

            saveInvoices(it)
        }    }
    init{
        indents.observeForever{

            saveIndents(it)
        }    }
    private fun saveIndents(indents: List<Indent>){
        Coroutines.io{

            db.getIndentsDao().deleteAll()
            db.getIndentsDao().saveAllIndents(indents)
        }
    }
    private fun saveStores(stores: List<Store>){
        Coroutines.io{

            db.getStoreDao().deleteAll()
            db.getStoreDao().saveAllOrganizationStores(stores)
        }
    }
    private fun saveInvoices(invoices: List<Invoice>){
        Coroutines.io{

            db.getInvoicesDao().deleteAll()
            db.getInvoicesDao().saveAllInvoices(invoices)
        }
    }
    private suspend fun fetchStores(username: String, organization_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.organizationStores(username, organization_name)}
            stores.postValue(null)
            stores.postValue(response.stores)
        }
    }
    private suspend fun fetchInvoices(username: String, organization_name: String, project_name: String, store_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getInvoices(username, organization_name, project_name, store_name)}
            invoices.postValue(null)
            invoices.postValue(response.invoice)
        }
    }
    private suspend fun fetchIndents(username: String, organization_name: String, project_name: String, store_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getIndents(username, organization_name, project_name, store_name)}
            indents.postValue(null)
            indents.postValue(response.indent)
        }
    }
    private suspend fun fetchStock(username: String, organization_name: String, project_name: String, store_name: String){
        if(isFetchNeeded()){
            val response = apiRequest{api.getStock(username, organization_name, project_name, store_name)}
            stores.postValue(null)
            stores.postValue(response.stores)
            //ToDo change the responsetype based on what to be displayed in gridview
        }
    }
    suspend fun getStores(username: String, organization_name: String): LiveData<List<Store>> {
        return withContext(Dispatchers.IO){
            fetchStores(username, organization_name)
            db.getStoreDao().getStores()
        }
    }
    suspend fun getStock(username: String, organization_name: String, project_name: String, store_name: String ): LiveData<List<Store>> {
        return withContext(Dispatchers.IO){
            fetchStock(username, organization_name, project_name, store_name)
            db.getStoreDao().getStores()
        }
    }
    suspend fun getAllAddedInvoices(
        username: String, organization_name: String,project_name: String, store_name: String
    ): LiveData<List<Invoice>> {
        return withContext(Dispatchers.IO)
        {
            fetchInvoices(username, organization_name, project_name, store_name)
            db.getInvoicesDao().getInvoices()

        }
    }
    suspend fun getAllAddedIndents(
        username: String, organization_name: String,project_name: String, store_name: String
    ): LiveData<List<Indent>> {
        return withContext(Dispatchers.IO)
        {
            fetchIndents(username, organization_name, project_name, store_name)
            db.getIndentsDao().getIndents()

        }
    }


    private fun isFetchNeeded(): Boolean{
        return true
    }
    suspend fun OrganizationStores(username: String, organization_name: String): StoreResponse {
        return apiRequest { api.organizationStores(username, organization_name) }
    }
    //add store
    suspend fun addStore(
        username: String, organization_name: String,project: String, storeName: String,storeType: String, storeLocation: String,storeDescription: String
    ) : StoreResponse{
        return apiRequest {
            api.addStore(
                username,
                organization_name,
                project,
                storeName,
                storeType,
                storeLocation,
                storeDescription
            )
        }
    }

    }


