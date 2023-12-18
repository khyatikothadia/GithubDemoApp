package com.example.githubdemo.data.api

import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GithubApiServiceTest {

    @Test
    fun testGetUserInfo() = runBlocking {
        // Mock GithubApiService
        val apiService = mockk<GithubApiService>()

        // Mock response data
        val userInfo = UserInfo(name = "Test User", avatarUrl = "https://test.com/avatar.jpg")
        coEvery { apiService.getUserInfo("testUserId") } returns userInfo

        // Test the function
        val result = apiService.getUserInfo("testUserId")

        // Verify the result
        assertEquals(userInfo, result)
    }

    @Test
    fun testGetUserRepos() = runBlocking {
        // Mock GithubApiService
        val apiService = mockk<GithubApiService>()

        // Mock response data
        val userRepos = listOf(
            UserRepos(
                name = "Repo1",
                description = "Description1",
                updatedAt = "2022-01-01",
                count = "100",
                forks = 50
            ),
            UserRepos(
                name = "Repo2",
                description = "Description2",
                updatedAt = "2022-02-01",
                count = "150",
                forks = 30
            )
        )
        coEvery { apiService.getUserRepos("testUserId") } returns userRepos

        // Test the function
        val result = apiService.getUserRepos("testUserId")

        // Verify the result
        assertEquals(userRepos, result)
    }
}