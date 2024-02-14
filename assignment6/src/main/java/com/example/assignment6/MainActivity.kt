package com.example.assignment6

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.assignment6.account_viewmodel.AccountViewModel
import com.example.assignment6.navigation.Destination
import com.example.assignment6.navigation.NavigationInfo
import com.example.assignment6.ui.login_screen.LoginScreenViewModel
import com.example.assignment6.ui.register_screen.RegisterScreenViewModel
import com.example.assignment6.ui.theme.Assignment1Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val accountViewModel: AccountViewModel by viewModels()
    private val registerScreenViewModel: RegisterScreenViewModel by viewModels()
    private val loginScreenViewModel: LoginScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            Assignment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationInfo(
                        registerScreenViewModel = registerScreenViewModel,
                        loginScreenViewModel = loginScreenViewModel
                    )
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener {
            val currentUser = it.currentUser
            if (currentUser != null){
                Log.d("Tag1", "Home Screen")
                accountViewModel.definesStartDestination(
                    destination = Destination.Home
                )
            } else {
                Log.d("Tag1", "Login/Register Screen")
                accountViewModel.definesStartDestination(
                    destination = Destination.Auth
                )
            }
        }
    }
}
