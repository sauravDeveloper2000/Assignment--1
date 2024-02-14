package com.example.assignment6.ui.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.assignment6.R
import com.example.assignment6.components.HorizontalSpacer
import com.example.assignment6.components.VerticalSpacer

@Composable
fun HomeScreen(
    modifier: Modifier,
    onSignOutClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_screen_text)
        )
        VerticalSpacer(space = 10)
        Button(onClick = {
            onSignOutClick()
        }) {
            Text(text = "Sign Out")
        }
    }
}