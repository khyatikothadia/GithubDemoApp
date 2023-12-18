package com.example.githubdemo.data.api


/**
 * Class to handle network state: Success and Error
 */
sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error<T>(val errorMessage: String) : NetworkState<T>()
}