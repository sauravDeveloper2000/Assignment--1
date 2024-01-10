package com.example.assignment4.ui.user_posts

import com.example.assignment4.model_class.user_model_class.User
import com.example.assignment4.model_class.user_posts_model_class.UserPosts

sealed interface UserPostUiState {
    data object Loading: UserPostUiState
    data class SuccessOne(val userPosts: UserPosts): UserPostUiState
    data object Error: UserPostUiState
}