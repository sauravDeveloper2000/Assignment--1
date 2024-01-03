package com.example.assignment3

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment3.ui.screens.UserDetailScreen
import com.example.assignment3.ui.screens.UserListScreen
import com.example.assignment3.ui.screens.WelcomeScreen
import com.example.assignment3.ui.view_model_section.UserDetailViewModel
import com.example.assignment3.ui.view_model_section.WelcomeAndUserListScreenViewModel

/**
 * Define routes means give ids to each screen.
 */
enum class UserAppScreen {
    WelcomeScreen,
    UserListScreen,
    DetailScreen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navyController: NavHostController = rememberNavController(),
    firstViewModel: WelcomeAndUserListScreenViewModel,
    secondViewModel: UserDetailViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "User App",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navyController,
            startDestination = UserAppScreen.WelcomeScreen.name
        ) {
            composable(route = UserAppScreen.WelcomeScreen.name) {
                WelcomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    nextScreen = {
                        firstViewModel.generateAndInsertUsers(2)
                        navyController.navigate(route = UserAppScreen.UserListScreen.name)
                    }
                )
            }

            composable(route = UserAppScreen.UserListScreen.name) {
                UserListScreen(
                    users = firstViewModel.listOfUsers.collectAsState(initial = emptyList()),
                    innerPadding,
                    generateNewUser = {
                        firstViewModel.generateAndInsertUsers(1)
                    },
                    userDetailScreen = { user ->
                        navyController.navigate(route = UserAppScreen.DetailScreen.name + "?userId=${user.id}")
                    },
                    onUpButtonClick = {
                        navyController.popBackStack()
                    }
                )
            }

            composable(
                route = UserAppScreen.DetailScreen.name + "?userId={userId}",
                arguments = listOf(
                    navArgument(
                        name = "userId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            )
            { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("userId") ?: return@composable
                secondViewModel.getUserById(id)?.let {
                    UserDetailScreen(
                        id = id,
                        user = it,
                        deleteUser = {user ->
                            secondViewModel.deleteUser(user)
                            navyController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}