package com.example.assignment3.repository_section

import android.util.Log
import com.example.assignment3.database.User
import com.example.assignment3.database.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override suspend fun updateOrInsertUser(user: User) {
        dao.updateOrInsertUser(user)
        Log.d("Tag2", "User has been inserted into database.")
    }

    override suspend fun deleteExistingUser(user: User) {
        dao.deleteExistingUser(user)
    }

    override fun getAllUsersFromDb(): Flow<List<User>> = dao.getAllUsersFromDb()

    override suspend fun getUserById(id: Int): User = dao.getUserById(id)

}