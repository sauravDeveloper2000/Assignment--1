package com.example.assignment6.repository

import com.google.firebase.auth.FirebaseUser

interface AccountRepository {
    suspend fun signUp(
        email: String,
        password: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: () -> Unit
    )
    suspend fun doSignOutUser()

    suspend fun signIn(
        email: String,
        password: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: () -> Unit
    )
}