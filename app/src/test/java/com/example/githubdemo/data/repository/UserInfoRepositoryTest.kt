package com.example.githubdemo.data.repository

import com.example.githubdemo.data.UserInfoDataSource
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.data.remote.ResourceState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserInfoRepositoryTest {

    @MockK
    private lateinit var userInfoDataSource: UserInfoDataSource

    private lateinit var userInfoRepository: UserInfoRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userInfoRepository = UserInfoRepository(userInfoDataSource, testDispatcher)
    }

    @Test
    fun `getUserInfo() should return success state when user is found`() = runTest {

        // Mocking the response from the service (Assign)
        coEvery { userInfoDataSource.getUserInfo("testUserId") } returns Response.success(
            mockUserInfo()
        )

        //Act
        val result = userInfoRepository.getUserInfo("testUserId")

        //Assert
        result.drop(1).collect {
            assertEquals(it, ResourceState.Success(mockUserInfo()))
        }
    }

    @Test
    fun `getUserInfo() should return error state when exception occurs`() = runTest {

        // Mocking an exception being thrown from the service (Assign)
        coEvery { userInfoDataSource.getUserInfo("testUserId") } throws Exception("Test exception")

        //Act
        val result = userInfoRepository.getUserInfo("testUserId")

        //Assert
        result.drop(1).collect {
            assertEquals(it, ResourceState.Error<UserInfo>("Test exception"))
        }
    }

    @Test
    fun `getUserRepos() should return flow when user repos found`() = runTest(testDispatcher) {

        // Mocking the response from the service (Assign)
        coEvery { userInfoDataSource.getUserRepos("testUserId") } returns Response.success(
            mockUserRepos()
        )

        //Act
        val result = userInfoRepository.getUserReposDetails("testUserId")

        //Assert
        result.drop(1).collect {
            // advanceTimeBy(DELAY)
            assertEquals(it, ResourceState.Success(mockUserRepos()))
        }
    }

    @Test
    fun `getUserRepos() should return error state when exception occurs`() =
        runTest(testDispatcher) {

            // Mocking an exception being thrown from the service
            coEvery { userInfoDataSource.getUserRepos("testUserId") } throws Exception("Test exception")

            // Act
            val result = userInfoRepository.getUserReposDetails("testUserId")

            //Assert
            result.drop(1).collect {
                assertEquals(it, ResourceState.Error<UserInfo>("Test exception"))
            }
        }

    private fun mockUserInfo(): UserInfo {
        return UserInfo(name = "Test User", avatarUrl = "https://test.com/avatar.jpg")
    }

    private fun mockUserRepos(): List<UserRepos> {
        return listOf(
            UserRepos.fake(),
            UserRepos.fake(),
        )
    }
}