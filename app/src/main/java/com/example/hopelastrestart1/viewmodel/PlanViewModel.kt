package com.example.hopelastrestart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.data.network.responses.PlanResponse
import com.example.hopelastrestart1.model.AddPlan
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.model.GetPlans
import com.example.hopelastrestart1.util.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception
import java.util.HashMap

class PlanViewModel(private val apiService: ApiService) : ViewModel() {

    fun addPlan(addPlan: AddPlan) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.addPlan(addPlan)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }


    fun getPlans(getPlans: GetPlans) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getPlans(getPlans)))
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
