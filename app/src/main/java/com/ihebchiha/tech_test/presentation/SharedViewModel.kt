package com.ihebchiha.tech_test.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ihebchiha.tech_test.domain.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * This view model serves the purpose of sharing data between views
 * A solution for passing objects between screen
 * ***/
@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    var repository by  mutableStateOf<Repo?>(null)
    private set

    fun addRepository(repo: Repo){
        repository = repo
    }
}