package com.example.assignment3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.ui.screens.WelcomeScreen

/**
 * Define routes means give ids to each screen.
 */
enum class UserAppScreen{
    WelcomeScreen,
    UserListScreen,
    DetailScreen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navConroller: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User App")}
            )
        }
    ) { innerPadding ->
        NavHost(navController = navConroller, startDestination = UserAppScreen.WelcomeScreen.name){
            composable(route = UserAppScreen.WelcomeScreen.name){
                WelcomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}