package com.example.githubdemo.data.datasource

import com.example.githubdemo.data.remote.ApiService
import com.example.githubdemo.data.remote.UserInfoRemoteDataSource
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserInfoRemoteDataSourceTest {

    @MockK
    private lateinit var apiService: ApiService

    private lateinit var userInfoRemoteDataSource: UserInfoRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userInfoRemoteDataSource = UserInfoRemoteDataSource(apiService)
    }

    @Test
    fun `getUserInfo returns result`() = runTest {

        //Assign
        val userInfo = UserInfo(name = "Test", avatarUrl = "Test")

        coEvery { apiService.getUserInfo("testUserId") } returns Response.success(userInfo)

        //Act
        val result = userInfoRemoteDataSource.getUserInfo("testUserId")

        //Assert
        assertNotNull(result)
    }

    @Test
    fun `getUserRepos returns result`() = runTest {

        //Assign
        val userRepos = listOf(UserRepos.fake(), UserRepos.fake(), UserRepos.fake())

        coEvery { apiService.getUserRepos("testUserId") } returns Response.success(userRepos)

        //Act
        val result = userInfoRemoteDataSource.getUserRepos("testUserId")

        //Assert
        assertNotNull(result)

    }
}