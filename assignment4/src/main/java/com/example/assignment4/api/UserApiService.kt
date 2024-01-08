package com.example.assignment4.api

import com.example.assignment4.model_class.user_model_class.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("api/v2/users")
    suspend fun getUserInfo(
        @Query("size") size: Int = 1
    ): User

}