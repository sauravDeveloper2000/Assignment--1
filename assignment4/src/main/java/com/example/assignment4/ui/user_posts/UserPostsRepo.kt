package com.example.assignment4.ui.user_posts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.assignment4.api.UserPostsApiService
import com.example.assignment4.model_class.user_posts_model_class.UserPosts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPostsRepo @Inject constructor(
    private val userPostsApiService: UserPostsApiService
) {
    fun getUsersPosts(): Flow<PagingData<UserPosts>> =
        Pager(
            config = PagingConfig(pageSize = 40,  prefetchDistance = 20)
        ) {
            PaginationSourceForUserPosts(userPostsApiService = userPostsApiService)
        }.flow

    suspend fun getUserPostById(
        id: String
    ): UserPosts{
        return userPostsApiService.getUserPostByItsId(userPostId = id)
    }
}