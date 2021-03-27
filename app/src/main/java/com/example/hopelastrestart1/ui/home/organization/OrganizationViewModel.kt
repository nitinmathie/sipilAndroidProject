package com.example.hopelastrestart1.ui.home.organization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hopelastrestart1.data.network.responses.OrganizationResponse
import com.example.hopelastrestart1.data.repository.OrganizationRepository
import com.example.hopelastrestart1.model.AddOrgModel
import com.example.hopelastrestart1.model.GetOrgModel
import com.example.hopelastrestart1.util.Resource
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class OrganizationViewModel(private val repository: OrganizationRepository, ) : ViewModel() {

    val organizations by lazyDeferred {
        //repository.getOrganizations("")
    }

    /* suspend fun userOrgsGet(userEmail:String,userToken:String) =
         withContext(Dispatchers.IO) { repository.getOrganizations(userEmail,userToken) }*/
    suspend fun userOrgs(getOrgModel: GetOrgModel) =
        withContext(Dispatchers.IO) { repository.getOrganizations(getOrgModel) }

    /*  suspend fun userOrganizations(
          email: String,
      ) = withContext(Dispatchers.IO) { repository.userOrganizations(email) }*/

    //adding an organization
    suspend fun addOrganization(addOrgModel: AddOrgModel) = withContext(Dispatchers.IO)
    {
        repository.addOrganization(addOrgModel)
    }

    /*suspend fun addOrg(addOrgModel: AddOrgModel)= liveData<Resource<OrganizationResponse>> {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.addOrganization(addOrgModel)))
        } catch (exception: Exception)
        {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }*/


}
