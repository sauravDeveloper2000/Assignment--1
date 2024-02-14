package com.example.assignment6.ui.register_screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.assignment6.R
import com.example.assignment6.components.ErrorItem
import com.example.assignment6.components.VerticalSpacer

@Composable
fun RegisterScreen(
    modifier: Modifier,
    backToLoginScreen: () -> Unit,
    registerScreenViewModel: RegisterScreenViewModel
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var passwordVisibility2 by remember {
        mutableStateOf(false)
    }
    var userEmail by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var emailError by remember {
        mutableStateOf(false)
    }
    var emailErrorText by remember {
        mutableStateOf<String?>(value = null)
    }
    var passWordError by remember {
        mutableStateOf(false)
    }
    var passwordErrorText by remember {
        mutableStateOf<String?>(null)
    }
    var confirmPasswordError by remember {
        mutableStateOf(false)
    }
    var confirmPasswordErrorText by remember {
        mutableStateOf<String?>(null)
    }
    val context = LocalContext.current
    var responseError by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        topBar = {
            TopBarOfRegisterScreen(
                navigateToLoginScreen = {
                    backToLoginScreen()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Outlined TextField for Email-ID
            OutlinedTextField(
                value = userEmail,
                onValueChange = {
                    userEmail = it
                },
                label = {
                    Text(text = stringResource(id = R.string.email_field))
                },
                isError = emailError,
                supportingText = {
                    emailErrorText?.let {
                        Text(text = it)
                    }
                }
            )
            // Outlined TextField for User-Password
            OutlinedTextField(
                value = userPassword,
                onValueChange = {
                    userPassword = it
                },
                label = {
                    Text(text = stringResource(id = R.string.password_field))
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                isError = passWordError,
                supportingText = {
                    passwordErrorText?.let {
                        Text(text = it)
                    }
                }
            )
            // Outlined TextField for Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                label = {
                    Text(text = stringResource(id = R.string.confirm_password))
                },
                visualTransformation = if (passwordVisibility2) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisibility2) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = {
                        passwordVisibility2 = !passwordVisibility2
                    }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                isError = confirmPasswordError,
                supportingText = {
                    confirmPasswordErrorText?.let {
                        Text(text = it)
                    }
                }
            )

            VerticalSpacer(space = 10)

            Button(
                onClick = {
                    //First Email Validation.
                    if (userEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(userEmail)
                            .matches()
                    ) {
                        emailError = false
                        emailErrorText = null
                    } else {
                        if (userEmail.isEmpty()) {
                            emailError = true
                            emailErrorText = "Please enter your Email-ID"
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                            emailError = true
                            emailErrorText = "Please enter an valid Email-ID without spaces"
                        }
                    }
                    // Second, User and Confirm Password validation
                    passWordError = userPassword.isEmpty()
                    passwordErrorText = if (userPassword.isEmpty()) "Please enter your password" else null
                    confirmPasswordError = confirmPassword.isEmpty()
                    confirmPasswordErrorText = if (confirmPassword.isEmpty()) "Please enter your confirm password" else null

                    if (!passWordError && !confirmPasswordError){
                        if (confirmPassword == userPassword){
                            Toast.makeText(context, "Validation of password succeeded", Toast.LENGTH_SHORT).show()
                        } else {
                            passWordError = true
                            confirmPasswordError = true
                            passwordErrorText = "Both password Not matching"
                            confirmPasswordErrorText = "Both password Not matching"
                        }
                    }
                    // Create account
                    if (emailError || passWordError || confirmPasswordError){
                        return@Button
                    } else {
                        registerScreenViewModel.signUp(
                            email = userEmail,
                            password = userPassword,
                            onSuccess = {},
                            onFailure = { responseError = " Unable to create a firebase user" }
                        )
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.register))
            }
            AnimatedVisibility(visible = responseError != null) {
                VerticalSpacer(space = 8)
                ErrorItem(text = responseError!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarOfRegisterScreen(
    navigateToLoginScreen: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.register_screen))
        },
        navigationIcon = {
            IconButton(onClick = {
                navigateToLoginScreen()
            }) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
            }
        }
    )
}