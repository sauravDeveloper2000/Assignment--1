package com.example.assignment3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}