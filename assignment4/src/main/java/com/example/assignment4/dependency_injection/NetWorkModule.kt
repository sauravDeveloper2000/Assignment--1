package com.example.assignment4.dependency_injection

import com.example.assignment4.api.UserApiService
import com.example.assignment4.api.UserPostsApiService
import com.example.assignment4.repository_section.Repo
import com.example.assignment4.ui.profile_screen.UserRepo
import com.example.assignment4.ui.user_posts.UserPostsRepo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    /**
     * Below retrofit instance is to fetch user information.
     * That's why its name is :- user-retrofit.
     */
    @Provides
    @Singleton
    @Named("user-retrofit")
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /**
     * Below retrofit instance is used to fetch users posts.
     * That's why its named is :- usersPosts-retrofit.
     */
    @Provides
    @Singleton
    @Named("userPosts")
    fun providesPostsRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /**
     * Below function provides Api to fetch user info.
     */
    @Provides
    @Singleton
    fun providesUserApiService(@Named("user-retrofit") retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    /**
     * Below function provides Api to fetch user posts.
     */
    @Provides
    @Singleton
    fun providesUsersPostsApiService(@Named("userPosts") retrofit: Retrofit): UserPostsApiService{
        return retrofit.create(UserPostsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepo(
        userApiService: UserApiService
    ): Repo {
        return UserRepo(userApiService = userApiService)
    }

    @Provides
    @Singleton
    fun providesUserPostsRepo(
        userPostsApiService: UserPostsApiService
    ): UserPostsRepo{
        return UserPostsRepo(userPostsApiService)
    }
}