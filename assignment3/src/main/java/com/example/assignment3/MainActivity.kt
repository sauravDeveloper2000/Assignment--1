package com.example.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.assignment3.ui.theme.Assignment1Theme
import com.example.assignment3.ui.view_model_section.UserDetailViewModel
import com.example.assignment3.ui.view_model_section.WelcomeAndUserListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val welcomeAndUserListScreenViewModel: WelcomeAndUserListScreenViewModel by viewModels()
    private val userDetailViewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                AppScreen(
                    firstViewModel = welcomeAndUserListScreenViewModel,
                    secondViewModel = userDetailViewModel
                )
            }
        }
    }
}
