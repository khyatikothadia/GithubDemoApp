package com.example.githubdemo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubdemo.R
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.ui.theme.GitHubAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colorScheme.primary,
            elevation = dimensionResource(R.dimen.margin_5)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
    }, content = { MainContent() })
}

@Composable
fun MainContent() {
    var text by remember { mutableStateOf("Hello") }

    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.margin_10),
                top = dimensionResource(R.dimen.margin_15)
            )
            .fillMaxWidth()
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(0.65f),
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(id = R.string.hint_user_id)) })
            Button(modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.margin_8),
                    top = dimensionResource(R.dimen.margin_15),
                    end = dimensionResource(R.dimen.margin_10)
                )
                .weight(0.35f), onClick = {

            }) {
                Text(
                    text = stringResource(R.string.search).uppercase(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Image(
            painter = painterResource(id = com.google.android.material.R.drawable.ic_clock_black_24dp),
            /* painter = rememberAsyncImagePainter(
                 model = ImageRequest.Builder(LocalContext.current)
                     .data(movie.images[0])
                     .size(Size.ORIGINAL) // Set the target size to load the image at.
                     .crossfade(true)
                     .transformations(CircleCropTransformation())
                     .build()
             )*/
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_15))
                .align(Alignment.CenterHorizontally),
            contentDescription = stringResource(id = R.string.image_content_description),
        )

        Text(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_8))
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.search),
            style = MaterialTheme.typography.bodyLarge, color = Color.Black
        )

        val list = listOf(
            UserRepos(
                name = "Rahul",
                description = "Hello",
                count = "100",
                updatedAt = "Yes",
                forks = 10
            ), UserRepos(
                name = "Rahul",
                description = "Hello",
                count = "100",
                updatedAt = "Yes",
                forks = 10
            ), UserRepos(
                name = "Rahul",
                description = "Hello",
                count = "100",
                updatedAt = "Yes",
                forks = 10
            ), UserRepos(
                name = "Rahul",
                description = "Hello",
                count = "100",
                updatedAt = "Yes",
                forks = 10
            ), UserRepos(
                name = "Rahul",
                description = "Hello",
                count = "100",
                updatedAt = "Yes",
                forks = 10
            )
        )
        RepositoryList(list)
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview() {
    GitHubAppTheme {
        HomeScreen()
    }
}