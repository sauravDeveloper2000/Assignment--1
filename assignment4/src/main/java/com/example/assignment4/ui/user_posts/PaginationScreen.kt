package com.example.assignment4.ui.user_posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assignment4.R
import com.example.assignment4.model_class.user_posts_model_class.UserPosts

@Composable
fun PaginationScreen(
    userPostsViewModel: UserPostsViewModel
) {
    val userPosts = userPostsViewModel.usesPosts.collectAsLazyPagingItems()

    Scaffold {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            UserPostsScreen(
                modifier = Modifier.fillMaxSize(),
                userPosts = userPosts
            )
        }
    }
}

@Composable
fun UserPostsScreen(
    modifier: Modifier = Modifier,
    userPosts: LazyPagingItems<UserPosts>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3)
    ){
        items(items = userPosts){userPost ->
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(userPost.downloadUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }
    }
}