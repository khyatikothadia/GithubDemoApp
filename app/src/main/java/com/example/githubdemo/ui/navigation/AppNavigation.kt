package com.example.githubdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githubdemo.ui.screens.HomeScreen
import com.example.githubdemo.ui.screens.RepositoryDetailsScreen
import com.example.githubdemo.ui.viewmodel.UserInfoViewModel

@Composable
fun AppNavigationGraph(navController: NavHostController) {

    val userInfoViewModel: UserInfoViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(onRepoClick = { repositoryId ->
                navController.navigate(Screen.RepositoryDetails.createRoute(repositoryId))
            }, userInfoViewModel = userInfoViewModel)
        }

        composable(
            route = Screen.RepositoryDetails.route,
            arguments = Screen.RepositoryDetails.navArguments
        ) { backStackEntry ->
            RepositoryDetailsScreen(
                onClickBack = { navController.popBackStack() },
                backStackEntry.arguments?.getInt("repositoryId") ?: 0,
                userInfoViewModel = userInfoViewModel
            )
        }
    }

}
