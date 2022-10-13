package com.ihebchiha.tech_test.presentation.repos_list.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ihebchiha.tech_test.domain.model.Repo

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepoItem(repo: Repo, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(25.dp), onClick = {
            onClick()
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(text = "Repository Name: ${repo.fullName}")
            Text(text = "Forks: ${repo.forks}")
            Text(text = "Open Issues: ${repo.openIssues}")
            Text(text = "Watchers: ${repo.watchers}")
        }
    }
}