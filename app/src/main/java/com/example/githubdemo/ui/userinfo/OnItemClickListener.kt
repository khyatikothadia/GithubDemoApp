package com.example.githubdemo.ui.userinfo

import com.example.githubdemo.data.model.UserRepos

/**
 * Interface definition for a callback to be invoked when an item is clicked.
 */
interface OnItemClickListener {

    /**
     * Called when an item in the user repository details is clicked.
     *
     * @param userRepoDetails The details of the clicked user repository.
     */
    fun onItemClick(userRepoDetails: UserRepos)
}