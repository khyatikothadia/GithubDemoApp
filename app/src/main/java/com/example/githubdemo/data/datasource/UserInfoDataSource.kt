package com.example.githubdemo.data.datasource

import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import retrofit2.Response

interface UserInfoDataSource {

    suspend fun getUserInfo(userId: String): Response<UserInfo>
    suspend fun getUserRepos(userId: String): Response<List<UserRepos>>
}