package com.example.assignment4.ui.user_posts

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assignment4.model_class.user_posts_model_class.UserPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    private val userPostsRepo: UserPostsRepo
): ViewModel() {

    var userPost: UserPostUiState by mutableStateOf(UserPostUiState.Loading)
        private set


    val usesPosts: Flow<PagingData<UserPosts>> =
        userPostsRepo.getUsersPosts().cachedIn(viewModelScope)

    fun getUserById(id: String){
        viewModelScope.launch {
            userPost = UserPostUiState.Loading
             userPost = try {
                val response = userPostsRepo.getUserPostById(id)
                 Log.d("Tag1", "$response")
                UserPostUiState.SuccessOne(userPosts = response)
            } catch (e: IOException){
                UserPostUiState.Error
            }
        }
    }
}