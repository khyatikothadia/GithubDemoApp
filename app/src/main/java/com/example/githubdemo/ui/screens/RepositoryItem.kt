package com.example.githubdemo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.githubdemo.R
import com.example.githubdemo.data.model.UserRepos

@Composable
fun RepositoryItem(userRepos: UserRepos) {
    Card(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.margin_6),
                end = dimensionResource(id = R.dimen.margin_16),
                top = dimensionResource(id = R.dimen.margin_10)
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.margin_5)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape

    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_4))) {
            Text(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.margin_10),
                    end = dimensionResource(id = R.dimen.margin_10),
                    top = dimensionResource(id = R.dimen.margin_5)
                ),
                text = userRepos.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.margin_10),
                    end = dimensionResource(id = R.dimen.margin_10),
                    top = dimensionResource(id = R.dimen.margin_5),
                    bottom = dimensionResource(id = R.dimen.margin_5)
                ), text = userRepos.description, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}