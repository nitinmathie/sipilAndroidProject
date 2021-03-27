package com.example.hopelastrestart1.ui.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.hopelastrestart1.data.db.entities.User
import com.example.hopelastrestart1.data.repository.UserRepository
import com.example.hopelastrestart1.model.LoginModel
import com.example.hopelastrestart1.model.SignUpModel


class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(loginModel: LoginModel
    ) = withContext(Dispatchers.IO) { repository.userLogin(loginModel) }

    suspend fun userSignup(
        SignUpModel: SignUpModel
    ) = withContext(Dispatchers.IO) { repository.userSignup(SignUpModel) }

    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)

}