package com.example.assignment4.ui.profile_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment4.model_class.user_model_class.User
import com.example.assignment4.repository_section.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    var _user = MutableStateFlow<UserUiState>(UserUiState.Loading)
    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            _user.value = UserUiState.Loading
             _user.value = try {
                 UserUiState.OnSuccess(user = repo.getUserInfo())
            } catch (e: Exception) {
                 Log.d("Tag1", "$e")
                UserUiState.OnError
            }
        }
    }
}