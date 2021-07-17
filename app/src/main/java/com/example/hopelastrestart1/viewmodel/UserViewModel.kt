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

class UserViewModel(private val apiService: ApiService) : ViewModel() {

    fun UpdateUserProfile(updateProfile: UpdateProfile) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.updateProfile(updateProfile)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }

    fun addUser(addUser: AddUser) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addUser(addUser)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }


    }

    fun getUsers(getUsers: GetUsers) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getUsers(getUsers)))
        } catch (exception: Exception) {
            emit(Resource.error(data = apiService.getUsers(getUsers), message = exception.message ?: "Error occured"))
        }


    }


    fun login(loginModel: LoginModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.login(loginModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }

    fun signUp(signUpModel: SignUpModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.login(signUpModel)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }

    fun getCurrentUserProfile(getUserProfile: GetUserProfile) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getCurrentUserProfile(getUserProfile)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }

    }

    fun updateUserRoles(updateRoles: UpdateUserRoles) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.updateUserRoles(updateRoles)))
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


}

/*  fun createUser(data:HashMap<String, String>)= liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data=apiService.createUser(data)))
      }catch (exception: Exception){
          emit(Resource.error(data=null,message = exception.message?:"Error occured"))
      }
  }
8
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
