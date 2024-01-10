package com.example.assignment4.ui.user_posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assignment4.OnErrorOne
import com.example.assignment4.OnLoading
import com.example.assignment4.R
import com.example.assignment4.model_class.user_posts_model_class.UserPosts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetails(
    userPostUiState: UserPostUiState,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = "Post Details",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { innerPadding ->
        var uriHandler = LocalUriHandler.current
        when (userPostUiState) {
            UserPostUiState.Error -> {
                OnErrorOne(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            UserPostUiState.Loading -> {
                OnLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            is UserPostUiState.SuccessOne -> {
                UserPostDetails(
                    userPosts = userPostUiState.userPosts,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onRepoUrlClick = {url ->
                        uriHandler.openUri(url)
                    }
                )
            }
        }
    }
}

@Composable
fun UserPostDetails(
    userPosts: UserPosts,
    modifier: Modifier,
    onRepoUrlClick: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp)
    ) {
        item {
            Card {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userPosts.downloadUrl)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.no_internet),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(
                        text = "Author:- ${userPosts.author}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Width:- ${userPosts.width}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Height:- ${userPosts.height}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row {
                        Text(
                            text = "Url:- ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            modifier = Modifier.clickable {
                                   onRepoUrlClick(userPosts.url)
                            },
                            text = " ${userPosts.url}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}