package com.ihebchiha.tech_test.utils

sealed class UIState(){
    object Success: UIState()
    object Loading: UIState()
    object Error: UIState()
    object Idle: UIState()
}
