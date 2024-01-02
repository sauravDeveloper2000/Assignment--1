package com.example.assignment3.repository_section

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.assignment3.database.User

interface UserRepository {
    suspend fun updateOrInsertUser(user: User)

    suspend fun deleteExistingUser(user: User)

    fun getAllUsersFromDb(): kotlinx.coroutines.flow.Flow<List<User>>

    suspend fun getUserById(id: Int): User?
}