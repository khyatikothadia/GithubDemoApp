package com.example.githubdemo.data.api

import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface to the backend API.
 */
interface GithubApiService {

    /**
     * Get user info. Returns a [UserInfo] API response.
     */
    @GET("users/{userId}")
    suspend fun getUserInfo(@Path(value = "userId") userId: String): UserInfo

    /**
     * Get user repos. Returns a list of [UserRepos] API response.
     */
    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path(value = "userId") userId: String): List<UserRepos>
}