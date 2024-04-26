package com.example.githubdemo.data.remote

import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface to the backend API.
 */
interface ApiService {

    /**
     * Get user info. Returns a [UserInfo] API response.
     */
    @GET("users/{userId}")
    suspend fun getUserInfo(@Path(value = "userId") userId: String): Response<UserInfo>

    /**
     * Get user repos. Returns a list of [UserRepos] API response.
     */
    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path(value = "userId") userId: String): Response<List<UserRepos>>
}