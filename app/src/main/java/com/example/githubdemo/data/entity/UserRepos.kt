package com.example.githubdemo.data.entity

import com.squareup.moshi.Json

/**
 * UserRepos model returned by the API.
 */

data class UserRepos(
    @Json(name = "id") val repositoryId: Int?,
    val name: String?,
    val description: String?,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "stargazers_count") val count: String?,
    val forks: Int?
) {
    companion object {

        /**
         * Creates a fake repo object for preview and testing purpose
         */
        fun fake() = UserRepos(
            repositoryId = 1,
            name = "Fake",
            description = "This is temporary fake description",
            forks = 20,
            updatedAt = null,
            count = "123"
        )
    }
}
