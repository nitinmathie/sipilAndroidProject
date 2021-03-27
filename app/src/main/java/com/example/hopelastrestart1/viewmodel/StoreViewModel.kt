package com.example.hopelastrestart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.data.network.responses.AuthResponse
import com.example.hopelastrestart1.data.network.responses.UserResponse
import com.example.hopelastrestart1.model.*
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception
import java.util.HashMap

class StoreViewModel(private val apiService: ApiService) : ViewModel() {

    fun addStore(addStore: AddStore) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addStore(addStore)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getStores(getStore: GetStores) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getStores(getStore)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }


}


