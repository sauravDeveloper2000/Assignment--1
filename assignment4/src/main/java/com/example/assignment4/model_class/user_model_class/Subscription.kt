package com.example.assignment4.model_class.user_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    @SerialName(value = "payment_method")
    val paymentMethod: String,
    val plan: String,
    val status: String,
    val term: String
)