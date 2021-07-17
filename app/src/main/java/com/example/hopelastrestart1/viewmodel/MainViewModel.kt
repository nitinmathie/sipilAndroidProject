package com.example.hopelastrestart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.model.AddOrgModel
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetOrgProjectRoles
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.HashMap

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    fun getOrgs(getOrgModel: GetOrgModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getOrgs(getOrgModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun getRoles(getOrgProjectRoles: GetOrgProjectRoles) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getRoles(getOrgProjectRoles)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }

    fun addOrg(addOrgModel: AddOrgModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addOrganization(addOrgModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }
}

/*  fun createUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.createUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }

  fun updateUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.updateUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }
  fun deleteUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.deleteUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }*/
