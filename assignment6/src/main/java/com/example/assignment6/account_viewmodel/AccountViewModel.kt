package com.example.assignment6.account_viewmodel

import androidx.lifecycle.ViewModel
import com.example.assignment6.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(): ViewModel() {
    var _startDestination = MutableStateFlow<Destination>(Destination.Auth)
        private set

    fun definesStartDestination(
        destination: Destination
    ){
        _startDestination.value = destination
    }
}