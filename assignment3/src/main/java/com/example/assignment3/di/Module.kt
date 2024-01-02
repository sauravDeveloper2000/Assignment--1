package com.example.assignment3.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.assignment3.database.UserDatabase
import com.example.assignment3.repository_section.UserRepository
import com.example.assignment3.repository_section.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): UserDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = UserDatabase::class.java,
            name = "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRepositoryInstance(database: UserDatabase): UserRepository{
        return UserRepositoryImpl(database.userDao)
    }
}