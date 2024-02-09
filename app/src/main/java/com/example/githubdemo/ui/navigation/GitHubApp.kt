package com.example.githubdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GitHubApp() {
    val navController = rememberNavController()
    AppNavigationGraph(navController = navController)
}
