package com.example.assignment3.ui.view_model_section

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.assignment3.database.User
import com.example.assignment3.repository_section.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    var user by mutableStateOf<User?>(null)
        private set
    var listItemIndex by mutableIntStateOf(0)
        private set

    fun setListItemIndexProperty(index: Int){
        listItemIndex = index
    }
    fun getUserById(id: Int): User?{
        viewModelScope.launch {
            user = userRepository.getUserById(id)
        }
        return user
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            userRepository.deleteExistingUser(user)
        }
    }
}