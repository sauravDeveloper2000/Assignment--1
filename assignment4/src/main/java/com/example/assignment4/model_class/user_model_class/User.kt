package com.example.assignment4.model_class.user_model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val address: Address,
    val avatar: String,
    @SerialName(value = "credit_card")
    val creditCard: CreditCard,
    @SerialName(value = "date_of_birth")
    val dateOfBirth: String,
    val email: String,
    val employment: Employment,
    @SerialName(value = "first_name")
    val firstName: String,
    val gender: String,
    val id: Int,
    @SerialName(value = "last_name")
    val lastName: String,
    val password: String,
    @SerialName(value = "phone_number")
    val phoneNumber: String,
    @SerialName(value = "social_insurance_number")
    val socialInsuranceNumber: String,
    val subscription: Subscription,
    val uid: String,
    val username: String
)