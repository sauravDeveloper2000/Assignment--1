package com.example.assignment2.ui.usersListScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assignment2.model_class.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    users: List<User>,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Users",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ) {
        ListsOfUsers(
            modifier = Modifier.padding(it),
            users = users
        )
    }
}

@Composable
fun ListsOfUsers(
    modifier: Modifier,
    users: List<User>
) {
    LazyColumn(
        modifier = modifier.padding(10.dp)
    ) {
        itemsIndexed(users) { index, user ->
            SingleUserItem(
                index = index, user = user
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun SingleUserItem(
    user: User,
    index: Int
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(40.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row{
                Text(
                    text = "UserID: ${user.userId}"
                )
                Text(
                    text = "Full Name:${user.fullName}",
                    textAlign = TextAlign.Right
                )
            }
            Row{
                Text(text = "User Name:${user.userName}")
                 Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Email ID:${user.emailId}"
                )

            }
            Button(
                modifier = Modifier.border(
                    width = 2.dp, color = Color.Black, shape = CircleShape
                ),
                onClick = {
                     Toast.makeText(context, "You have clicked on = ${index + 1}, 😁", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "${index + 1}")
            }
        }
    }
}
