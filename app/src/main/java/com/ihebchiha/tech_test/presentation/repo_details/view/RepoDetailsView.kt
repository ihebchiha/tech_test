package com.ihebchiha.tech_test.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RepoDetailsView(sharedViewModel: SharedViewModel){
   val repository by remember {
       mutableStateOf(sharedViewModel.repository)
   }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = repository!!.fullName, style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = repository!!.description)
        }
    }
}