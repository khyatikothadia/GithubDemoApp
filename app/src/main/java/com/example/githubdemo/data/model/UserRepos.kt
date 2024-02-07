package com.example.githubdemo.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * UserRepos model returned by the API.
 */

@Parcelize
data class UserRepos(
    val name: String,
    val description: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("stargazers_count") val count: String,
    val forks: Int
) : Parcelable
