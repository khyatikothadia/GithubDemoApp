package com.example.githubdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubdemo.data.api.NetworkState
import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.data.repository.UserInfoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UserInfoViewModelTest {

    // Mocks
    private lateinit var userInfoRepository: UserInfoRepository
    private lateinit var viewModel: UserInfoViewModel

    // Observer for LiveData
    private lateinit var userInfoObserver: Observer<UserInfo?>
    private lateinit var userReposObserver: Observer<List<UserRepos>?>

    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    // Rule to swap the background executor used by the Architecture Components with a different one
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        userInfoRepository = mockk()
        viewModel = UserInfoViewModel(userInfoRepository)

        userInfoObserver = mockk(relaxed = true)
        userReposObserver = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getUserInfo_should_update_live_data_on_success() = runTest {
        // Mocking the response from the repository
        coEvery { userInfoRepository.getUserInfo("testUserId") } returns NetworkState.Success(
            mockUserInfo()
        )

        // Observe userInfoLiveData
        viewModel.getUserInfoLiveData().observeForever(userInfoObserver)

        // Call the ViewModel function
        viewModel.getUserInfo("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserInfo("testUserId") }

        // Verify that LiveData was updated
        verify { userInfoObserver.onChanged(any()) }
    }

    @Test
    fun getUserReposDetails_should_update_live_data_on_success() = runTest {
        // Mocking the response from the repository
        coEvery { userInfoRepository.getUserReposDetails("testUserId") } returns NetworkState.Success(
            mockUserRepos()
        )

        // Observe UserReposLiveData
        viewModel.getUserReposLiveData().observeForever(userReposObserver)

        // Call the ViewModel function
        viewModel.getUserReposDetails("testUserId")

        // Verify repository method call
        coVerify { userInfoRepository.getUserReposDetails("testUserId") }

        // Verify that LiveData was updated
        verify { userReposObserver.onChanged(any()) }

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