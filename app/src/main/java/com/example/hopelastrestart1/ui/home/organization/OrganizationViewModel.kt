package com.example.hopelastrestart1.ui.home.organization

import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.repository.OrganizationRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrganizationViewModel(private val repository: OrganizationRepository

) : ViewModel() {


   // private  var repository: OrganizationRepository
    //val x  = repository.userOrganizations(email: String)

   val organizations by lazyDeferred {
       repository.getOrganizations("")
   }
    suspend fun organizations1(
        email: String,
    ) = withContext(Dispatchers.IO) { repository.getOrganizations(email) }
    suspend fun userOrganizations(
        email: String,
    ) = withContext(Dispatchers.IO) { repository.userOrganizations(email) }
    //adding an organization
    suspend fun addOrganization(
        orgName: String,
        orgEmail: String,
        orgLocation: String,
        orgUser: String
    ) = withContext(Dispatchers.IO) { repository.addOrganization(orgName, orgEmail,orgLocation, orgUser) }

}