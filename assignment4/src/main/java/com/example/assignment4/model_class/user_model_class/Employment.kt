package com.example.assignment4.model_class.user_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employment(
    @SerialName(value = "key_skill")
    val keySkill: String,
    val title: String
)