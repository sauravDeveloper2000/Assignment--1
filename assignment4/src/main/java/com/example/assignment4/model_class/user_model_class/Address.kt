package com.example.assignment4.model_class.user_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val state: String,
    @SerialName(value = "street_address")
    val streetAddress: String,
    @SerialName(value = "street_name")
    val streetName: String,
    @SerialName(value = "zip_code")
    val zipCode: String
)