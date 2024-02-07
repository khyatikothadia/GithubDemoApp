package com.example.githubdemo.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.githubdemo.R
import com.example.githubdemo.ui.theme.GitHubAppTheme

@Composable
fun RepositoryDetailsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                start = dimensionResource(id = R.dimen.margin_15),
                top = dimensionResource(id = R.dimen.margin_15),
                end = dimensionResource(id = R.dimen.margin_15),
            ),
        color = colorResource(id = R.color.card_background_color),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_5))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(dimensionResource(id = R.dimen.margin_10))
        ) {
            val (labelRepositoryDetails, labelName, repositoryName,
                labelDescription, description, labelUpdatedAt, updatedAt,
                labelCount, count, labelForks, forks, labelTotalForks, totalForks) = createRefs()

            Text(
                text = stringResource(id = R.string.repository_details).uppercase(),
                modifier = Modifier
                    .constrainAs(labelRepositoryDetails) {
                        top.linkTo(parent.top)
                    }
                    .padding(dimensionResource(id = R.dimen.margin_15))
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = stringResource(id = R.string.repository_name),
                modifier = Modifier
                    .constrainAs(labelName) {
                        start.linkTo(parent.start)
                        top.linkTo(labelRepositoryDetails.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_15)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_name),
                modifier = Modifier
                    .constrainAs(repositoryName) {
                        start.linkTo(parent.start)
                        top.linkTo(labelName.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_description),
                modifier = Modifier
                    .constrainAs(labelDescription) {
                        start.linkTo(parent.start)
                        top.linkTo(repositoryName.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_description),
                modifier = Modifier
                    .constrainAs(description) {
                        start.linkTo(parent.start)
                        top.linkTo(labelDescription.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_updated),
                modifier = Modifier
                    .constrainAs(labelUpdatedAt) {
                        start.linkTo(parent.start)
                        top.linkTo(description.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_updated),
                modifier = Modifier
                    .constrainAs(updatedAt) {
                        start.linkTo(parent.start)
                        top.linkTo(labelUpdatedAt.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_count),
                modifier = Modifier
                    .constrainAs(labelCount) {
                        start.linkTo(parent.start)
                        top.linkTo(updatedAt.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_count),
                modifier = Modifier
                    .constrainAs(count) {
                        start.linkTo(parent.start)
                        top.linkTo(labelCount.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_forks),
                modifier = Modifier
                    .constrainAs(labelForks) {
                        start.linkTo(parent.start)
                        top.linkTo(count.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_forks),
                modifier = Modifier
                    .constrainAs(forks) {
                        start.linkTo(parent.start)
                        top.linkTo(labelForks.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_total_forks),
                modifier = Modifier
                    .constrainAs(labelTotalForks) {
                        start.linkTo(parent.start)
                        top.linkTo(forks.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(id = R.string.repository_total_forks),
                modifier = Modifier
                    .constrainAs(totalForks) {
                        start.linkTo(parent.start)
                        top.linkTo(labelTotalForks.bottom)
                    }
                    .padding(top = dimensionResource(id = R.dimen.margin_5)),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitHubAppTheme {
        RepositoryDetailsScreen()
    }
}


