package com.example.githubdemo.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubdemo.R
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.ui.theme.GitHubAppTheme

@Composable
fun RepositoryList(userRepos: List<UserRepos?>, onRepoClick: (Int) -> Unit) {

    val translationY = remember { Animatable(500f) }

    LaunchedEffect(Unit) {
        translationY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500)
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.margin_10),
                bottom = dimensionResource(id = R.dimen.margin_10)
            )
            .graphicsLayer(
                translationY = translationY.value
            ),
        state = rememberLazyListState()
    ) {
        items(userRepos) { userRepos ->
            RepositoryItem(userRepos = userRepos) { repositoryId ->
                onRepoClick(repositoryId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryListPreview() {
    GitHubAppTheme {
        RepositoryList(
            userRepos = listOf(UserRepos.fake(), UserRepos.fake(), UserRepos.fake()),
            onRepoClick = {})
    }
}
