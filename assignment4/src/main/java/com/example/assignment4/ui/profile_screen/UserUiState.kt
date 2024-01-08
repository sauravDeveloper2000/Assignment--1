package com.example.assignment4.ui.profile_screen

import com.example.assignment4.model_class.user_model_class.User

sealed interface UserUiState {
    data object Loading: UserUiState
    data class OnSuccess(val user: User): UserUiState
    data object OnError: UserUiState
}