package com.ihebchiha.tech_test

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ihebchiha.tech_test.presentation.navigation.AppNavigationHost
import com.ihebchiha.tech_test.ui.theme.Tech_testTheme
import com.ihebchiha.tech_test.utils.NetworkConnectivityObserver
import com.ihebchiha.tech_test.utils.NetworkConnectivityWatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var networkWatcher: NetworkConnectivityWatcher


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tech_testTheme {
                //Initialize the network watcher
                networkWatcher = NetworkConnectivityWatcher(applicationContext)
                setContent {
                        val context = applicationContext
                        val navController = rememberNavController()
                    val networkStatus by networkWatcher.observe()
                        .collectAsState(initial = NetworkConnectivityObserver.NetworkStatus.Unavailable)
                    Surface(modifier = Modifier.fillMaxSize()) {
                            if (networkStatus != NetworkConnectivityObserver.NetworkStatus.Available) {
                                Toast.makeText(context, "No connectivity available", Toast.LENGTH_SHORT).show()
                            } else {
                                //initialize Navigation host
                                AppNavigationHost(navController = navController)
                            }
                        }
                    }
            }
        }
    }
}
