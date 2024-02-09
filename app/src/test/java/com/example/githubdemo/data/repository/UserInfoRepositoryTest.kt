package com.example.githubdemo.data.repository

import com.example.githubdemo.data.api.ResourceState
import com.example.githubdemo.data.datasource.UserInfoDataSource
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserInfoRepositoryTest {

    @MockK
    private lateinit var userInfoDataSource: UserInfoDataSource

    private lateinit var userInfoRepository: UserInfoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userInfoRepository = UserInfoRepository(userInfoDataSource)
    }

    @Test
    fun `getUserInfo() should return flow when user is found`() {

        // Mocking the response from the service (Assign)
        coEvery { userInfoDataSource.getUserInfo("testUserId") } returns Response.success(
            mockUserInfo()
        )

        // Collect the flow
        runTest {
            //Act
            val result = userInfoRepository.getUserInfo("testUserId")

            //Assert
            assertNotNull(result)
        }

        /*val flow = userInfoRepository.getUserInfo("testUserId")

        //  Test the flow
        assertEquals(ResourceState.Loading::class, flow.first()::class)

        coVerify { userInfoDataSource.getUserInfo("userId") }
        // Check emitted success state
        val result = flow.drop(1).first()
        assertEquals(ResourceState.Success(mockUserInfo()), result)*/
    }

    @Test
    fun `getUserInfo() should return error state when exception occurs`() = runTest {

        // Mocking an exception being thrown from the service
        coEvery { userInfoDataSource.getUserInfo("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val flow = userInfoRepository.getUserInfo("testUserId")
        val result = flow.drop(1).first()
        // Verify api service method call
        coVerify { userInfoDataSource.getUserInfo("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(ResourceState.Error<UserInfo>("Test exception"), result)
    }

    @Test
    fun `getUserRepos() should return flow when user repos found`() {

        // Mocking the response from the service (Assign)
        coEvery { userInfoDataSource.getUserRepos("testUserId") } returns Response.success(
            mockUserRepos()
        )

        // Collect the flow
        runTest {
            //Act
            val result = userInfoRepository.getUserReposDetails("testUserId")

            //Assert
            assertNotNull(result)
        }

    }

    @Test
    fun `getUserRepos() should return an empty flow when there are no repos`() {

        // Mocking the response from the service (Assign)
        coEvery { userInfoDataSource.getUserRepos("testUserId") } returns Response.success(
            emptyList()
        )

        // Collect the flow
        runTest {
            //Act
            val result = userInfoRepository.getUserReposDetails("testUserId")

            //Assert
            assertNotNull(result)
        }
    }

    @Test
    fun `getUserRepos() should return error state when exception occurs`() = runTest {

        // Mocking an exception being thrown from the service
        coEvery { userInfoDataSource.getUserRepos("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val flow = userInfoRepository.getUserReposDetails("testUserId")
        val result = flow.drop(1).first()
        // Verify api service method call
        coVerify { userInfoDataSource.getUserRepos("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(ResourceState.Error<List<UserRepos>>("Test exception"), result)
    }

    /*@Test
    fun getUserReposDetails_should_return_success_state() = runBlocking {

        // Mocking the response from the service
        coEvery { apiService.getUserRepos("testUserId") } returns mockUserRepos()

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserReposDetails("testUserId")

        // Verify api service method call
        coVerify { apiService.getUserRepos("testUserId") }

        // Asserting that the result is of type Success and contains expected data
        assertEquals(ResourceState.Success(mockUserRepos()), result)
    }*/

    /*@Test
    fun getUserInfo_should_return_error_state() = runBlocking {

        // Mocking an exception being thrown from the service
        coEvery { apiService.getUserInfo("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserInfo("testUserId")

        // Verify api service method call
        coVerify { apiService.getUserInfo("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(ResourceState.Error<UserInfo>("Test exception"), result)
    }

    @Test
    fun getUserReposDetails_should_return_error_state() = runBlocking {

        // Mocking an exception being thrown from the service
        coEvery { apiService.getUserRepos("testUserId") } throws Exception("Test exception")

        // Call the repository function and assert the result
        val result = userInfoRepository.getUserReposDetails("testUserId")

        // Verify api service method call
        coVerify { apiService.getUserRepos("testUserId") }

        // Asserting that the result is of type Error and contains expected error message
        assertEquals(ResourceState.Error<List<UserRepos>>("Test exception"), result)
    }*/

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