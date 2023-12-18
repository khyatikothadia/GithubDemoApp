package com.example.githubdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubdemo.data.api.NetworkState
import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.data.repository.UserInfoRepository
import com.example.githubdemo.util.Constants.TAG_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for managing and providing data related to user information
 * and repositories details to the associated UI components.
 *
 * @property userInfoRepository The repository responsible for fetching user information and repositories.
 */
class UserInfoViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {

    private val userInfoLiveData = MutableLiveData<UserInfo?>()
    private val userReposLiveData = MutableLiveData<List<UserRepos>?>()

    /**
     * Fetches user information for the specified [userId] and updates the [userInfoLiveData].
     * It observes the result using [NetworkState] to handle success and error states.
     *
     * @param userId The unique identifier of the user whose information is to be fetched.
     */
    fun getUserInfo(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = userInfoRepository.getUserInfo(userId)) {
                is NetworkState.Success -> {
                    userInfoLiveData.postValue(response.data)
                }

                is NetworkState.Error -> {
                    Log.e(TAG_ERROR, response.errorMessage)
                }

                else -> {
                    Log.e(TAG_ERROR, "Unknown Error")
                }
            }
        }
    }

    /**
     * Fetches user repository details for the specified [userId] and updates the [userReposLiveData].
     * It observes the result using [NetworkState] to handle success and error states.
     *
     * @param userId The unique identifier of the user whose information is to be fetched.
     */
    fun getUserReposDetails(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = userInfoRepository.getUserReposDetails(userId)) {
                is NetworkState.Success -> {
                    userReposLiveData.postValue(response.data)
                }

                is NetworkState.Error -> {
                    Log.e(TAG_ERROR, response.errorMessage)
                }

                else -> {
                    Log.e(TAG_ERROR, "Unknown Error")
                }
            }
        }
    }

    // Expose user info LiveData to the fragment
    fun getUserInfoLiveData(): LiveData<UserInfo?> = userInfoLiveData

    // Expose user repos LiveData to the fragment
    fun getUserReposLiveData(): LiveData<List<UserRepos>?> = userReposLiveData
}