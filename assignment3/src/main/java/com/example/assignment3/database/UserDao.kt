package com.example.assignment3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import java.util.concurrent.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun updateOrInsertUser(user: User)

    @Delete
    suspend fun deleteExistingUser(user: User)

    @Query("SELECT * FROM user ")
    fun getAllUsersFromDb(): kotlinx.coroutines.flow.Flow<List<User>>

    @Query("SELECT * FROM user WHERE id=:id")
    suspend fun getUserById(id: Int): User?
}