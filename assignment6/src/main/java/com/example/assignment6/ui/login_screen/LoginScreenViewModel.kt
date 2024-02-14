package com.example.assignment6.ui.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment6.repository.AccountRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel(){

    fun signInUser(
        email: String,
        password: String,
        onFailure: () -> Unit,
        onSuccess: (FirebaseUser?) -> Unit
    ){
        viewModelScope.launch {
            accountRepository.signIn(
                email = email,
                password = password,
                onSuccess = { firebaseUser ->
                    firebaseUser?.let { user ->
                        Log.d("Tag1", "Successfully signed in to firebase")
                        Log.d("Tag1", "User id: ${user.uid}")
                        Log.d("Tag1", "User name: ${user.displayName}")
                        Log.d("Tag1", "User email: ${user.email}")
                    }
                    onSuccess(firebaseUser)
                },
                onFailure = {
                    Log.d("Tag1", "Error occurred while signing in user")
                    onFailure()
                }
            )
        }
    }
}