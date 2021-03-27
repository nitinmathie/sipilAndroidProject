package com.example.hopelastrestart1.api

import com.example.hopelastrestart1.data.network.responses.OrganizationResponse


interface ApiHelper {

    suspend fun getOrgnizations(): List<OrganizationResponse>

  //  suspend fun getMoreUsers(): List<ApiUser>

//    suspend fun getUsersWithError(): List<ApiUser>

}