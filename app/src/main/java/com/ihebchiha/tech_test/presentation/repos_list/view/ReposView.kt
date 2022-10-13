package com.ihebchiha.tech_test.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ihebchiha.tech_test.R
import com.ihebchiha.tech_test.common.createToast
import com.ihebchiha.tech_test.domain.model.Repo
import com.ihebchiha.tech_test.presentation.navigation.Routes
import com.ihebchiha.tech_test.presentation.repos_list.ReposViewModel
import com.ihebchiha.tech_test.presentation.repos_list.composable.RepoItem
import com.ihebchiha.tech_test.ui.theme.greenBEF264
import com.ihebchiha.tech_test.utils.UIState
import timber.log.Timber

@Composable
fun ReposView(viewModel: ReposViewModel, sharedVM: SharedViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState(UIState.Idle)
    val context = LocalContext.current
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val errorMessage by viewModel.errorMessage.collectAsState("Error")

    val repositories by viewModel.repos.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
        viewModel.getJetbrainsRepos()
    }

    when (uiState) {
        UIState.Error -> {
            Timber.d("Error while retrieving data")
            LaunchedEffect(key1 = Unit) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = {
                    viewModel.refresh()
                }) {}
        }
        UIState.Loading -> {
            Timber.d("Loading ...")
            BoxWithConstraints(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 50.dp),
                        text = stringResource(id = R.string.no_item_message),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp), color = greenBEF264
                    )
                }
            }
        }
        UIState.Success -> {
            Timber.d("Data retrieved")
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = {
                    viewModel.refresh()
                }) {
                LazyColumn {
                    items(items = repositories, itemContent = { item ->
                        RepoItem(repo = item) {
                            sharedVM.addRepository(item)
                            navController.navigate(Routes.RepoDetails.route)
                        }
                    })
                }
            }
        }
        else -> {}
    }
}