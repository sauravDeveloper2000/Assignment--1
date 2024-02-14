package com.example.assignment6.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AccountRepository {

    override suspend fun signUp(
        email: String,
        password: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: () -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val firebaseUser = task.result.user
                onSuccess(firebaseUser)
            }
            .addOnCanceledListener {
                onFailure()
            }
    }
    override suspend fun doSignOutUser(){
        firebaseAuth.signOut()
    }

    override suspend fun signIn(
        email: String,
        password: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: () -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                try {
                    val firebaseUser = task.result.user
                    onSuccess(firebaseUser)
                } catch (e: Exception){
                    onFailure()
                }
            }
            .addOnCanceledListener {
                onFailure()
            }
    }
}