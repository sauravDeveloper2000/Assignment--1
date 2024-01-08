package com.example.assignment4.ui.profile_screen

import com.example.assignment4.api.UserApiService
import com.example.assignment4.model_class.user_model_class.User
import com.example.assignment4.repository_section.Repo

class UserRepo(
    private val userApiService: UserApiService
): Repo {
    override suspend fun getUserInfo(): User {
        return userApiService.getUserInfo()
    }
}