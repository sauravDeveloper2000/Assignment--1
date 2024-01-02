package com.example.assignment3.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Long,
    val userName: String,
    val fullName: String,
    val emailId: String
)
