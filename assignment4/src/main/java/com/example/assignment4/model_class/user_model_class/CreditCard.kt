package com.example.assignment4.model_class.user_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditCard(
    @SerialName(value = "cc_number")
    val ccNumber: String
)