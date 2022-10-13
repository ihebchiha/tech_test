package com.ihebchiha.tech_test.utils

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityObserver {

    fun observe(): Flow<NetworkStatus>

    enum class NetworkStatus {
        Available, Unavailable, Losing, Lost
    }
}