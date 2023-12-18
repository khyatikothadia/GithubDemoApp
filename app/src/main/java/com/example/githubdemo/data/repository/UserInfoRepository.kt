package com.example.githubdemo.data.repository

import com.example.githubdemo.data.api.GithubApiService
import com.example.githubdemo.data.api.NetworkState
import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.util.Constants.DELAY
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Repository class responsible for fetching user information and repositories details
 * from the GitHub API.
 *
 * @property githubApiService The service responsible for interacting with the GitHub API.
 */
class UserInfoRepository @Inject constructor(private val githubApiService: GithubApiService) {


    /**
     * Fetches user information for the specified [userId] from the GitHub API.
     *
     * @param userId The unique identifier of the GitHub user.
     * @return [NetworkState.Success] with the retrieved [UserInfo] if successful,
     *         [NetworkState.Error] with an error message otherwise.
     */
    suspend fun getUserInfo(
        userId: String
    ): NetworkState<UserInfo>? {
        val response: UserInfo
        return try {
            response = githubApiService.getUserInfo(userId)
            NetworkState.Success(response)
        } catch (e: Exception) {
            e.message?.let { NetworkState.Error(it) }
        }
    }

    /**
     * Fetches user's public repositories details for the specified [userId] from the GitHub API.
     *
     * @param userId The unique identifier of the GitHub user.
     * @return [NetworkState.Success] with the retrieved [UserRepos] if successful,
     *         [NetworkState.Error] with an error message otherwise.
     */
    suspend fun getUserReposDetails(
        userId: String
    ): NetworkState<List<UserRepos>>? {
        val response: List<UserRepos>
        return try {
            delay(DELAY)
            response = githubApiService.getUserRepos(userId)
            NetworkState.Success(response)
        } catch (e: Exception) {
            e.message?.let { NetworkState.Error(it) }
        }
    }
}