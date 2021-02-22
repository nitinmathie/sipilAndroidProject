package com.example.hopelastrestart1.ui.home.plen.tabs

import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.repository.PlanRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanViewModel(private val repository: PlanRepository

) : ViewModel() {


    // private  var repository: OrganizationRepository
    //val x  = repository.userOrganizations(email: String)

    val plans by lazyDeferred {
        repository.getPlans("", "","")
    }
    suspend fun plans1(
        username: String,
        organization_name: String,
        project_name: String,
    ) = withContext(Dispatchers.IO) { repository.getPlans(username , organization_name , project_name ) }

    //adding an organization
    suspend fun addPlan(planName: String,
                        planDesc: String,
                        planTemp: String,
                        projName: String,
                        orgName: String,
                        username: String

    ) = withContext(Dispatchers.IO) { repository.addPlan(planName, planDesc ,planTemp,   projName ,orgName, username) }

}