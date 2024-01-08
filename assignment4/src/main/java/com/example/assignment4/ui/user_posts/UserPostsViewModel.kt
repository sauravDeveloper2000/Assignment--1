package com.example.assignment4.ui.user_posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assignment4.model_class.user_posts_model_class.UserPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    private val userPostsRepo: UserPostsRepo
): ViewModel() {

    val usesPosts: Flow<PagingData<UserPosts>> =
        userPostsRepo.getUsersPosts().cachedIn(viewModelScope)
}