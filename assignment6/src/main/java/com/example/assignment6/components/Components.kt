package com.example.assignment6.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    space: Int
) {
    Spacer(modifier = Modifier.height(space.dp))
}

@Composable
fun HorizontalSpacer(
    space: Int
) {
    Spacer(modifier = Modifier.width(space.dp))
}
@Composable
fun ErrorItem(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.error
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            color = color
        )
    }
}