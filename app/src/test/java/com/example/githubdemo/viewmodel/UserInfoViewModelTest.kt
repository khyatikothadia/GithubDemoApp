package com.example.githubdemo.viewmodel

import app.cash.turbine.test
import com.example.githubdemo.data.remote.ResourceState
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.data.repository.UserInfoRepository
import com.example.githubdemo.ui.viewmodel.UserInfoViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserInfoViewModelTest {

    // Mocks
    @MockK
    private lateinit var userInfoRepository: UserInfoRepository

    private lateinit var userInfoViewModel: UserInfoViewModel

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    // Rule to swap the background executor used by the Architecture Components with a different one
    //@get:Rule
    //val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        userInfoViewModel = UserInfoViewModel(userInfoRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUserInfo() should update flow state on success`() = runTest {
        // Mocking the response from the repository (Assign)
        coEvery { userInfoRepository.getUserInfo("testUserId") } returns flowOf(
            ResourceState.Success(mockUserInfo())
        )

        //Act
        userInfoViewModel.getUserInfo("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserInfo("testUserId") }

        //Assert
        userInfoViewModel.userInfo.test {
            assertEquals(ResourceState.Success(mockUserInfo()), awaitItem())
        }
    }

    @Test
    fun `getUserInfo() should update flow state on error`() = runTest {
        // Mocking the response from the repository (Assign)
        coEvery { userInfoRepository.getUserInfo("testUserId") } returns flowOf(
            ResourceState.Error("Test Error")
        )

        //Act
        userInfoViewModel.getUserInfo("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserInfo("testUserId") }

        //Assert
        userInfoViewModel.userInfo.test {
            assertEquals(ResourceState.Error<UserInfo>("Test Error"), awaitItem())
        }
    }

    @Test
    fun `getUserReposDetails() should update flow state on success`() = runTest {
        // Mocking the response from the repository (Assign)
        coEvery { userInfoRepository.getUserReposDetails("testUserId") } returns flowOf(
            ResourceState.Success(mockUserRepos())
        )

        //Act
        userInfoViewModel.getUserReposDetails("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserReposDetails("testUserId") }

        // Assert
        userInfoViewModel.userRepositories.test {
            assertEquals(ResourceState.Success(mockUserRepos()), awaitItem())
        }
    }

    @Test
    fun `getUserReposDetails() should update flow state on error`() = runTest {
        // Mocking the response from the repository (Assign)
        coEvery { userInfoRepository.getUserReposDetails("testUserId") } returns flowOf(
            ResourceState.Error("Test Error")
        )

        //Act
        userInfoViewModel.getUserReposDetails("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserReposDetails("testUserId") }

        // Assert
        userInfoViewModel.userRepositories.test {
            assertEquals(ResourceState.Error<List<UserRepos>>("Test Error"), awaitItem())
        }
    }


    private fun mockUserRepos(): List<UserRepos> {
        return listOf(
            UserRepos.fake(),
            UserRepos.fake()
        )
    }

    private fun mockUserInfo(): UserInfo {
        return UserInfo(name = "Test User", avatarUrl = "https://test.com/avatar.jpg")
    }
}