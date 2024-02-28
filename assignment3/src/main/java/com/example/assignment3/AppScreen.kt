package com.example.assignment3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
            modifier = Modifier.padding(innerPadding),
            navController = navyController,
            startDestination = UserAppScreen.WelcomeScreen.name
        ) {
            composable(route = UserAppScreen.WelcomeScreen.name) {
                WelcomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    nextScreen = {
                        firstViewModel.generateAndInsertUsers(5)
                        navyController.navigate(route = UserAppScreen.UserListScreen.name)
                    }
                )
            }

            composable(route = UserAppScreen.UserListScreen.name) {
                UserListScreen(
                    users = firstViewModel.listOfUsers.collectAsState(initial = emptyList()).value,
                    generateNewUser = {
                        firstViewModel.generateAndInsertUsers(2)
                    },
                    userDetailScreen = { user, index ->
                        secondViewModel.setListItemIndexProperty(index = index)
                        navyController.navigate(route = UserAppScreen.DetailScreen.name + "?userId=${user.id}")
                    }
                )
            }

            composable(
                route = UserAppScreen.DetailScreen.name + "?userId={userId}",
                arguments = listOf(
                    navArgument(
                        name = "userId",
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
                        id = secondViewModel.listItemIndex,
                        user = it,
                        deleteUser = { user ->
                            secondViewModel.deleteUser(user)
                            navyController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}