package com.example.githubdemo.data.repository

import com.example.githubdemo.data.api.GithubApiService
import com.example.githubdemo.data.api.NetworkState
import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserInfoRepositoryTest {

    private lateinit var githubApiService: GithubApiService
    private lateinit var userInfoRepository: UserInfoRepository

    @Before
    fun setUp() {
        githubApiService = mockk()
        userInfoRepository = UserInfoRepository(githubApiService)
    }

    @Test
    fun getUserInfo_should_return_success_state() = runBlocking {

        // Mocking the response from the service
        coEvery { githubApiService.getUserInfo("testUserId") } returns mockUserInfo()

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserInfo("testUserId")

        // Verify api service method call
        coVerify { githubApiService.getUserInfo("testUserId") }

        // Asserting that the result is of type Success and contains expected data
        assertEquals(NetworkState.Success(mockUserInfo()), result)

    }

    @Test
    fun getUserReposDetails_should_return_success_state() = runBlocking {

        // Mocking the response from the service
        coEvery { githubApiService.getUserRepos("testUserId") } returns mockUserRepos()

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserReposDetails("testUserId")

        // Verify api service method call
        coVerify { githubApiService.getUserRepos("testUserId") }

        // Asserting that the result is of type Success and contains expected data
        assertEquals(NetworkState.Success(mockUserRepos()), result)
    }

    @Test
    fun getUserInfo_should_return_error_state() = runBlocking {

        // Mocking an exception being thrown from the service
        coEvery { githubApiService.getUserInfo("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserInfo("testUserId")

        // Verify api service method call
        coVerify { githubApiService.getUserInfo("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(NetworkState.Error<UserInfo>("Test exception"), result)
    }

    @Test
    fun getUserReposDetails_should_return_error_state() = runBlocking {

        // Mocking an exception being thrown from the service
        coEvery { githubApiService.getUserRepos("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserReposDetails("testUserId")

        // Verify api service method call
        coVerify { githubApiService.getUserRepos("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(NetworkState.Error<List<UserRepos>>("Test exception"), result)
    }

    private fun mockUserInfo(): UserInfo {
        return UserInfo(name = "Test User", avatarUrl = "https://test.com/avatar.jpg")
    }

    private fun mockUserRepos(): List<UserRepos> {
        return listOf(
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
    }
}