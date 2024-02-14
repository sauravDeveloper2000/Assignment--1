package com.example.assignment5

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.assignment5.ui.home_screen.HomeScreen
import com.example.assignment5.ui.loging_screen.LoginScreen
import com.example.assignment5.ui.loging_screen.LoginScreenViewModel
import com.example.assignment5.ui.theme.Assignment1Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginScreenViewModel: LoginScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            Assignment1Theme {
//                NavigationInfo(loginScreenViewModel = loginScreenViewModel)
//            }
//        }
        lifecycleScope.launch {
            loginScreenViewModel.isAuthenticated.collect{ isAuthenticated ->
                if (isAuthenticated){
                    setContent {
                        Assignment1Theme {
                            NavigationInfo(
                                startDestination = Routes.HomeScreen.name
                            )
                        }
                    }
              } else {
                setContent {
                        Assignment1Theme {
                            LoginScreen(
                                doLoginUser = {
                                    loginScreenViewModel.saveIsAuthenticated(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}