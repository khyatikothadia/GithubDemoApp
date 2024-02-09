package com.example.githubdemo.data.datasource

import com.example.githubdemo.data.api.ApiService
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import retrofit2.Response
import javax.inject.Inject

class UserInfoDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    UserInfoDataSource {

    override suspend fun getUserInfo(userId: String): Response<UserInfo> {
        return apiService.getUserInfo(userId)
    }

    override suspend fun getUserRepos(userId: String): Response<List<UserRepos>> {
        return apiService.getUserRepos(userId)
    }
}