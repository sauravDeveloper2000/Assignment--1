package com.example.assignment4.model_class.user_posts_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPosts(
    val author: String,
    @SerialName(value = "download_url")
    val downloadUrl: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)