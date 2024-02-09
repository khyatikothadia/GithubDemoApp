package com.example.githubdemo.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen(route = "Home")

    data object RepositoryDetails : Screen(
        route = "repositoryDetail/{repositoryId}",
        navArguments = listOf(navArgument(name = "repositoryId") {
            type = NavType.IntType
        })
    ) {
        fun createRoute(repositoryId: Int) = "repositoryDetail/${repositoryId}"
    }
}
