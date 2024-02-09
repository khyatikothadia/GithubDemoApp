package com.example.githubdemo.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.githubdemo.R
import com.example.githubdemo.data.api.ResourceState
import com.example.githubdemo.data.entity.UserInfo
import com.example.githubdemo.data.entity.UserRepos
import com.example.githubdemo.ui.components.EmptyState
import com.example.githubdemo.ui.components.Loader
import com.example.githubdemo.ui.components.TopBar
import com.example.githubdemo.ui.theme.GitHubAppTheme
import com.example.githubdemo.util.AppConstants.BUTTON_WEIGHT
import com.example.githubdemo.util.AppConstants.TEXT_WEIGHT
import com.example.githubdemo.util.CoreUtility
import com.example.githubdemo.util.CoreUtility.isInternetConnected
import com.example.githubdemo.viewmodel.UserInfoViewModel


@Composable
fun HomeScreen(
    onRepoClick: (Int) -> Unit = {},
    userInfoViewModel: UserInfoViewModel,
) {

    val userInfo by userInfoViewModel.userInfo.collectAsState()
    val repositoryList by userInfoViewModel.userRepositories.collectAsState()

    MainContent(
        onRepoClick = onRepoClick,
        userInfoState = userInfo,
        repositoryListState = repositoryList,
        onSearchClick = { userId ->
            userInfoViewModel.getUserInfo(userId = userId)
            userInfoViewModel.getUserReposDetails(userId = userId)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(
    onRepoClick: (Int) -> Unit,
    userInfoState: ResourceState<UserInfo?>,
    repositoryListState: ResourceState<List<UserRepos>?>,
    onSearchClick: (String) -> Unit,
) {

    var lastEnteredUserId by rememberSaveable { mutableStateOf("") }
    val controller = LocalSoftwareKeyboardController.current
    var userId by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar(title = R.string.app_name) }) {
        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.margin_10),
                    top = dimensionResource(R.dimen.margin_15)
                )
                .fillMaxWidth()
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(modifier = Modifier.weight(TEXT_WEIGHT),
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text(stringResource(id = R.string.hint_user_id)) })
                Button(
                    enabled = userId.isNotEmpty(),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.margin_8),
                            top = dimensionResource(R.dimen.margin_15),
                            end = dimensionResource(R.dimen.margin_10)
                        )
                        .weight(BUTTON_WEIGHT),
                    onClick = {
                        controller?.hide()
                        if (isInternetConnected(context)) {
                            if (CoreUtility.isValidString(userId)) {
                                if (userId.isNotEmpty() && userId != lastEnteredUserId) {
                                    lastEnteredUserId = userId
                                    onSearchClick(userId.trim())
                                }
                            } else {
                                Toast.makeText(
                                    context, R.string.error_message, Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context, R.string.error_no_network, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                    Text(
                        text = stringResource(R.string.search).uppercase(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            when (userInfoState) {
                is ResourceState.Empty -> {}
                is ResourceState.Loading -> {
                    Loader()
                }

                is ResourceState.Success -> {
                    val userInfoData = userInfoState.data
                    UserInfo(userInfo = userInfoData)
                }

                is ResourceState.Error -> {}

            }

            when (repositoryListState) {
                is ResourceState.Empty -> {}
                is ResourceState.Loading -> {
                    Loader()
                }

                is ResourceState.Success -> {
                    val repositoryListData = repositoryListState.data
                    if (repositoryListData?.isNotEmpty() == true) {
                        RepositoryList(repositoryListData, onRepoClick)
                    } else {
                        EmptyState()
                    }
                }

                is ResourceState.Error -> {
                    EmptyState()
                }
            }
        }
    }
}

@Composable
fun UserInfo(userInfo: UserInfo?) {

    val translationY = remember { Animatable(500f) }

    LaunchedEffect(Unit) {
        translationY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                translationY = translationY.value
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(userInfo?.avatarUrl)
                    .size(Size.ORIGINAL)
                    .crossfade(true).build()
            ),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_15))
                .align(Alignment.CenterHorizontally),
            contentDescription = stringResource(id = R.string.label_user_image),
        )

        Text(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_8))
                .align(Alignment.CenterHorizontally),
            text = userInfo?.name ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    GitHubAppTheme {
        MainContent(
            onRepoClick = {},
            userInfoState =
            ResourceState.Success(
                UserInfo(
                    name = "Test",
                    avatarUrl = "https://avatars3.githubusercontent.com/u/583231?v=4"
                )
            ),
            repositoryListState = ResourceState.Success(listOf(UserRepos.fake())),
            onSearchClick = {}
        )
    }
}