package com.example.hopelastrestart1.ui.home.project.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.data.repository.OrganizationUserRepository
import com.example.hopelastrestart1.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: OrganizationUserRepository): ViewModel() {
    val projects by lazyDeferred {
        repository.getUsers("",organization_name = "")
    }
    val user_spin by lazyDeferred {
        repository.getUsers(username ="" ,organization_name = "")
    }
  //  val users : LiveData<List<UserAdded>> = repository.getusers_r()
    suspend fun users1(
        username: String,
        organization_name: String,
    ) = withContext(Dispatchers.IO) { repository.getUsers(username, organization_name) }
    suspend fun organizationUsers(
        username: String,
        organization_name: String,
    ) = withContext(Dispatchers.IO) { repository.OrganizationUsers(username, organization_name) }
    //adding an project change input
    suspend fun addUser(
        username:String,
        organization_name: String,
        name: String,
        email: String,
        project: String,
        role: String
    ) = withContext(Dispatchers.IO) { repository.addUser(username,organization_name,name,email,project,role) }
    suspend fun users_byrole(
        username: String,
        organization_name: String
    ) = withContext(Dispatchers.IO) { repository.getUsers(username, organization_name) }
    val myLiveDataList: LiveData<List<UserAdded>>
        get() = repository.getusers_by_role()

}