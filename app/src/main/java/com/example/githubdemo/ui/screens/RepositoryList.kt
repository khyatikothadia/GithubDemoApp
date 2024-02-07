package com.example.githubdemo.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.githubdemo.R
import com.example.githubdemo.data.model.UserRepos

@Composable
fun RepositoryList(userRepos: List<UserRepos>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.margin_10),
                bottom = dimensionResource(id = R.dimen.margin_10)
            )
            .height(dimensionResource(id = R.dimen.recycler_view_height)),
        state = rememberLazyListState()
    ) {
        items(userRepos) { userRepos ->
            RepositoryItem(userRepos)
        }
    }
}