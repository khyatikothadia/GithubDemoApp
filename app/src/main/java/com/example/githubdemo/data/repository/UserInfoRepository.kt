package com.example.githubdemo.data.repository

import com.example.githubdemo.data.UserInfoDataSource
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.data.remote.ResourceState
import com.example.githubdemo.util.AppConstants.DELAY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository class responsible for fetching user information and repositories details
 * from the GitHub API.
 *
 * @property userInfoDataSource The datasource responsible for interacting with the GitHub API.
 */
class UserInfoRepository @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {

    /**
     * Fetches user information for the specified [userId] from the GitHub API.
     *
     * @param userId The unique identifier of the GitHub user.
     * @return [ResourceState.Success] with the retrieved [UserInfo] if successful,
     *         [ResourceState.Error] with an error message otherwise.
     */
    suspend fun getUserInfo(
        userId: String
    ): Flow<ResourceState<UserInfo?>> {
        return flow {
            emit(ResourceState.Loading())

            val response = userInfoDataSource.getUserInfo(userId)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()))
            } else {
                if (response.code() == 403) {
                    emit(ResourceState.Error("Something went wrong, please try after sometime"))
                }
                emit(ResourceState.Error("Error fetching data"))
            }
        }.flowOn(ioDispatcher)
            .catch { exception ->
                emit(ResourceState.Error(exception.localizedMessage ?: "Unknown error"))
            }
    }

    /**
     * Fetches user's public repositories details for the specified [userId] from the GitHub API.
     *
     * @param userId The unique identifier of the GitHub user.
     * @return [ResourceState.Success] with the retrieved [UserRepos] if successful,
     *         [ResourceState.Error] with an error message otherwise.
     */
    suspend fun getUserReposDetails(
        userId: String
    ): Flow<ResourceState<List<UserRepos>?>> {

        return flow {

            emit(ResourceState.Loading())
            delay(DELAY)
            val response = userInfoDataSource.getUserRepos(userId)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()))
            } else {
                if (response.code() == 403) {
                    emit(ResourceState.Error("Something went wrong, please try after sometime"))
                }
                emit(ResourceState.Error("Error fetching data"))
            }
        }.flowOn(ioDispatcher).catch { exception ->
            emit(ResourceState.Error(exception.localizedMessage ?: "Unknown error"))
        }
    }
}