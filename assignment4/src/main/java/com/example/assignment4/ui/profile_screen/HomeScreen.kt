package com.example.assignment4.ui.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment4.ui.user_posts.PostDetails
import com.example.assignment4.ui.user_posts.UserPostsViewModel

enum class Routes {
    UserScreen,
    PostScreen
}

@Composable
fun HomeScreen(
    navHostController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
    userPostsViewModel: UserPostsViewModel
) {
    NavHost(navController = navHostController, startDestination = Routes.UserScreen.name) {
        composable(route = Routes.UserScreen.name) {
            UserProfileScreen(
                userUiState = userViewModel._user.collectAsState().value,
                userPosts = userPostsViewModel.usesPosts.collectAsLazyPagingItems(),
                details = { userPostId ->
                    userPostsViewModel.getUserById(userPostId)
                    navHostController.navigate(route = Routes.PostScreen.name)
                }
            )
        }
        composable(
            route = Routes.PostScreen.name
        ) {
            PostDetails(
               userPostsViewModel.userPost,
                navigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}