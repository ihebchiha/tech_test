package com.ihebchiha.tech_test.presentation.repos_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihebchiha.tech_test.domain.model.Repo
import com.ihebchiha.tech_test.domain.usecase.GetReposUseCase
import com.ihebchiha.tech_test.utils.Resource
import com.ihebchiha.tech_test.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(private val getReposUseCase: GetReposUseCase) :
    ViewModel() {

    private val _repos = MutableStateFlow<List<Repo>>(emptyList())
    val repos = _repos

    private val _uiState = MutableSharedFlow<UIState>()
    val uiState = _uiState

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing


    fun getJetbrainsRepos() {
        getReposUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.emit(UIState.Success)
                    _repos.emit(result.data as List<Repo>)
                }
                is Resource.Error -> {
                    _uiState.emit(UIState.Error)
                    _errorMessage.emit(
                        result.message ?: "error occurred while processing your request."
                    )
                }
                is Resource.Loading -> {
                    _uiState.emit(UIState.Loading)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        getJetbrainsRepos()
        _isRefreshing.update { false }
    }


}