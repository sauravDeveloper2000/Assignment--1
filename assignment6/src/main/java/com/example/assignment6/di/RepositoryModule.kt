package com.example.assignment6.di

import com.example.assignment6.repository.AccountRepository
import com.example.assignment6.repository.AccountRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAccountRepository(auth: FirebaseAuth): AccountRepository{
        return AccountRepositoryImpl(firebaseAuth = auth)
    }
}