package com.example.assignment6.navigation

sealed class Destination(
    val route: String
) {
    data object Auth: Destination(route = "Auth"){
        data object SignUp: Destination(route = "SignUp")
        data object SignIn: Destination(route = "SignIn")
    }
    data object Home: Destination(route = "Home")
}