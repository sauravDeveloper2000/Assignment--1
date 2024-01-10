package com.example.assignment4.ui.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assignment4.OnErrorOne
import com.example.assignment4.OnLoading
import com.example.assignment4.R
import com.example.assignment4.items
import com.example.assignment4.model_class.user_model_class.User
import com.example.assignment4.model_class.user_posts_model_class.UserPosts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    userUiState: UserUiState,
    userPosts: LazyPagingItems<UserPosts>,
    details: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "User Profile Screen",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { innerPadding ->
        when (userUiState) {
            UserUiState.Loading -> {
                OnLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            UserUiState.OnError -> {
                OnErrorOne(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            is UserUiState.OnSuccess -> {
                ProfileScreen(
                    user = userUiState.user,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    userPosts = userPosts,
                    postDetails = {
                        details(it)
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    user: User,
    modifier: Modifier,
    userPosts: LazyPagingItems<UserPosts>,
    postDetails: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp)
    ) {
        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            UserDetails(user = user)
        }

        items(
            userPosts
        ) { userPosts ->
            UserPosts(
                userPost = userPosts,
                modifier = Modifier
                    .clickable {
                        postDetails(userPosts.id)
                    }
            )
        }
    }
}

@Composable
fun UserDetails(
    user: User
) {
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(84.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatar)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.no_internet),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = "Name:- ${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Job:- ${user.employment.title}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "EmailId:- ${user.email}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        color = MaterialTheme.colorScheme.primary,
                        width = 2.dp,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clip(shape = MaterialTheme.shapes.medium)
            ) {
                Row(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = "Street :- ${user.address.streetName}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = "City:- ${user.address.city}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = "Country:- ${user.address.country}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = "Address:- ${user.address.streetAddress}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = "State:- ${user.address.state}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = "CountryCode:- ${user.address.zipCode}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            Text(
                text = "Subscription Status:- ${user.subscription.status}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Plan:- ${user.subscription.plan}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Divider(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
        }
    }
}

@Composable
fun UserPosts(
    userPost: UserPosts,
    modifier: Modifier
) {
    Card(
        modifier = modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15))
                .aspectRatio(1.5f),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(userPost.downloadUrl)
                .crossfade(true)
                .build(),
            contentDescription = null
        )
    }
}