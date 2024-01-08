package com.example.assignment4.api

import com.example.assignment4.model_class.user_posts_model_class.UserPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface UserPostsApiService {
    @GET("v2/list")
    suspend fun getUsersPosts(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 30
    ): List<UserPosts>
}