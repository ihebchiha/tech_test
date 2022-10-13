package com.ihebchiha.tech_test.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ihebchiha.tech_test.domain.model.Repo
import com.ihebchiha.tech_test.presentation.RepoDetailsView
import com.ihebchiha.tech_test.presentation.ReposView
import com.ihebchiha.tech_test.presentation.SharedViewModel

sealed class Routes(val route: String) {
    object ReposList : Routes("/repos_list")
    object RepoDetails : Routes("/repo_details")
}

@Composable
fun AppNavigationHost(navController: NavHostController) {
    val sharedVM: SharedViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Routes.ReposList.route) {
        composable(Routes.ReposList.route) {
            ReposView(hiltViewModel(), sharedVM, navController = navController)
        }
        composable(Routes.RepoDetails.route) {
            RepoDetailsView(sharedVM)
        }
    }
}
