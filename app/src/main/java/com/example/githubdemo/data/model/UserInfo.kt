package com.example.githubdemo.data.model

import com.google.gson.annotations.SerializedName

/**
 * UserInfo model returned by the API.
 */
data class UserInfo(
    val name: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
