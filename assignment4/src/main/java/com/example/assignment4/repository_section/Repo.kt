package com.example.assignment4.repository_section

import com.example.assignment4.model_class.user_model_class.User

interface Repo {
    suspend fun getUserInfo(): User
}