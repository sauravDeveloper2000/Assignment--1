package com.example.assignment4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment4.model_class.user_model_class.User
import com.example.assignment4.ui.profile_screen.UserUiState
import com.example.assignment4.ui.profile_screen.UserViewModel
import com.example.assignment4.ui.theme.Assignment1Theme
import com.example.assignment4.ui.user_posts.PaginationScreen
import com.example.assignment4.ui.user_posts.UserPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val userPostsViewModel: UserPostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PaginationScreen(
                        userPostsViewModel = userPostsViewModel
                    )
                }
            }
        }
    }
}
