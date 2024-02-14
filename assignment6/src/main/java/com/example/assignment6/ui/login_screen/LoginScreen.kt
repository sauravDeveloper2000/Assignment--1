package com.example.assignment6.ui.login_screen

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import com.example.assignment6.R
import com.example.assignment6.components.ErrorItem
import com.example.assignment6.components.VerticalSpacer

@Composable
fun LoginScreen(
    modifier: Modifier, createNewAccount: () -> Unit,
    loginScreenViewModel: LoginScreenViewModel
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var userEmail by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
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
    var responseError by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userEmail,
            onValueChange = {
                userEmail = it
            },
            label = {
                Text(text = stringResource(id = R.string.email_field))
            }, isError = emailError, supportingText = {
                emailErrorText?.let {
                    Text(text = it)
                }
            })
        OutlinedTextField(value = userPassword,
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
            })
        VerticalSpacer(space = 10)
        Button(onClick = {
            //First Email Validation.
            if (userEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
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

            if (emailError || passWordError) {
                return@Button
            } else {
                loginScreenViewModel.signInUser(
                    email = userEmail,
                    password = userPassword,
                    onSuccess = {},
                    onFailure = {
                        responseError = "Failed to Login"
                    }
                )
            }
        }) {
            Text(text = stringResource(id = R.string.login))
        }

        VerticalSpacer(space = 10)

        AnimatedVisibility(visible = responseError != null) {
            VerticalSpacer(space = 8)
            ErrorItem(text = responseError!!)
        }

        VerticalSpacer(space = 10)

        Text(
            modifier = Modifier.clickable {
                createNewAccount()
            },
            text = stringResource(id = R.string.if_login_failed),
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}