package com.example.assignment6.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.assignment6.account_viewmodel.AccountViewModel
import com.example.assignment6.ui.home_screen.HomeScreen
import com.example.assignment6.ui.login_screen.LoginScreen
import com.example.assignment6.ui.login_screen.LoginScreenViewModel
import com.example.assignment6.ui.register_screen.RegisterScreen
import com.example.assignment6.ui.register_screen.RegisterScreenViewModel

@Composable
fun NavigationInfo(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    accountViewModel: AccountViewModel = hiltViewModel(),
    registerScreenViewModel: RegisterScreenViewModel,
    loginScreenViewModel: LoginScreenViewModel
    ) {
    val startDestination by accountViewModel._startDestination.collectAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination.route // Destination.Auth.route
    ) {
        composable(
            route = Destination.Home.route
        ){
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                onSignOutClick = {
                    registerScreenViewModel.doSignOutUser()
                }
            )
        }

        navigation(
            route = Destination.Auth.route,
            startDestination = Destination.Auth.SignUp.route
        ){
            // Sign In - Login To Existing Account
            composable(route = Destination.Auth.SignIn.route){
                LoginScreen(
                    modifier = Modifier.fillMaxSize(),
                    createNewAccount = {
                        navController.navigate(Destination.Auth.SignUp.route)
                    },
                    loginScreenViewModel = loginScreenViewModel
                )
            }
            // SignUp - Create New Account
            composable(route = Destination.Auth.SignUp.route){
                RegisterScreen(
                    modifier = Modifier.fillMaxSize(),
                    backToLoginScreen = {
                        navController.navigate(Destination.Auth.SignIn.route)
                    },
                   registerScreenViewModel = registerScreenViewModel
                )
            }
        }
    }
}