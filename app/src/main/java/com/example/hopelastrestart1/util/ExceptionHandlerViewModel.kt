package com.example.hopelastrestart1.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopelastrestart1.api.ApiHelper
import com.example.hopelastrestart1.data.network.responses.OrganizationResponse

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ExceptionHandlerViewModel(
    private val apiHelper: ApiHelper,
 ) : ViewModel() {

    private val users = MutableLiveData<Resource<List<OrganizationResponse>>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
      //  users.postValue(Resource.error("Something Went Wrong", null))
    }
    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            users.postValue(Resource.loading(null))
            val usersFromApi = apiHelper.getOrgnizations()
           // users.postValue(Resource.success(usersFromApi))
        }
    }

    fun getUsers(): LiveData<Resource<List<OrganizationResponse>>> {
        return users
    }

}