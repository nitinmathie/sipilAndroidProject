package com.example.hopelastrestart1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.Plan
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.PlanResponse
import com.example.hopelastrestart1.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanRepository(private val api: MyApi,
                     private val db: AppDatabase
) : SafeApiRequest(){
    private val plans = MutableLiveData<List<Plan>>()
    init{
        plans.observeForever{
            savePlans(it)
        }    }
    private fun savePlans(plans: List<Plan>){
        Coroutines.io{
            db.getProjectPlanDao().deleteAll()
            db.getProjectPlanDao().saveAllProjectPlans(plans)
        }
    }
    private suspend fun fetchPlans(username: String, organization_name: String, project_name: String){
        if(isFetchNeeded()){

            val response = apiRequest{api.projectPlans(username, organization_name, project_name)}
            //clear db if it exists, actually a new record can't be added with same id, nut a new id is being added why?

            plans.postValue(response.plans)
        }
    }
    suspend fun getPlans(username: String, organization_name: String, project_name: String): LiveData<List<Plan>> {
        return withContext(Dispatchers.IO){
            fetchPlans(username, organization_name, project_name)
            db.getProjectPlanDao().getPlans()
        }
    }
    private fun isFetchNeeded(): Boolean{
        return true
    }
    suspend fun ProjectPlans(username: String, organization_name: String, project_name: String): PlanResponse {
        return apiRequest { api.projectPlans(username, organization_name, project_name) }
    }
    //add project
    suspend fun addPlan( planName: String,
                         planDesc: String,
                        planTemp: String,
                        projName: String,
                        orgName: String,
                        username: String): PlanResponse {
        return apiRequest { api.addPlan(planName, planDesc, planTemp, projName,orgName, username) }
    }
}

