package com.example.assignment6.ui.register_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment6.repository.AccountRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    fun signUp(
        email: String,
        password: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {
            accountRepository.signUp(
                email = email,
                password = password,
                onSuccess = { firebaseUser ->
                    firebaseUser?.let {
                        Log.d("Tag1", "${firebaseUser.email}")
                        Log.d("Tag1", "${firebaseUser.displayName}")
                        Log.d("Tag1", "${firebaseUser.uid}")
                    }
                    onSuccess(firebaseUser)
                },
                onFailure = {
                    Log.d("Tag1", "Unable to create user.")
                    onFailure()
                }
            )
        }
    }
    fun doSignOutUser(){
        viewModelScope.launch {
            accountRepository.doSignOutUser()
        }
    }

}