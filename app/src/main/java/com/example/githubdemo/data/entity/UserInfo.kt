package com.example.githubdemo.data.entity

import com.squareup.moshi.Json

/**
 * UserInfo model returned by the API.
 */
data class UserInfo(
    val name: String?,
    @Json(name = "avatar_url") val avatarUrl: String?
)
