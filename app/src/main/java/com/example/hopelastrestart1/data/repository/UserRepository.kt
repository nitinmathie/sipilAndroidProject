package com.example.hopelastrestart1.data.repository

import com.example.hopelastrestart1.data.db.AppDatabase
import com.example.hopelastrestart1.data.db.entities.User
import com.example.hopelastrestart1.data.network.MyApi
import com.example.hopelastrestart1.data.network.SafeApiRequest
import com.example.hopelastrestart1.data.network.responses.AuthResponse
import com.example.hopelastrestart1.model.LoginModel
import com.example.hopelastrestart1.model.SignUpModel

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(loginModel: LoginModel): AuthResponse {
        return apiRequest { api.userLogin(loginModel) }
    }

    suspend fun userSignup(
        SignUpModel: SignUpModel
    ): AuthResponse {
        return apiRequest { api.userSignup(SignUpModel) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}