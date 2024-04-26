package com.example.githubdemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubdemo.data.remote.ResourceState
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.data.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for managing and providing data related to user information
 * and repositories details to the associated UI components.
 *
 * @property userInfoRepository The repository responsible for fetching user information and repositories.
 */

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) : ViewModel() {

    private val _userInfo: MutableStateFlow<ResourceState<UserInfo?>> =
        MutableStateFlow(ResourceState.Empty())
    private val _userRepositories: MutableStateFlow<ResourceState<List<UserRepos>?>> =
        MutableStateFlow(ResourceState.Empty())

    // Expose user info state flow to the UI
    val userInfo: StateFlow<ResourceState<UserInfo?>> = _userInfo

    // Expose user repos state flow to the UI
    val userRepositories: StateFlow<ResourceState<List<UserRepos>?>> = _userRepositories

    /**
     * Fetches user information for the specified [userId] and updates the [_userInfo].
     * It observes the result using [ResourceState] to handle success and error states.
     *
     * @param userId The unique identifier of the user whose information is to be fetched.
     */
    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            userInfoRepository.getUserInfo(userId).collectLatest { userInfo ->
                _userInfo.value = userInfo
            }
        }
    }

    /**
     * Fetches user repository details for the specified [userId] and updates the [_userRepositories].
     * It observes the result using [ResourceState] to handle success and error states.
     *
     * @param userId The unique identifier of the user whose information is to be fetched.
     */
    fun getUserReposDetails(userId: String) {
        viewModelScope.launch {
            userInfoRepository.getUserReposDetails(userId).collectLatest { repositories ->
                _userRepositories.value = repositories
            }
        }
    }
}
