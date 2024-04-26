package com.example.githubdemo.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.githubdemo.BuildConfig
import com.example.githubdemo.ui.navigation.GitHubApp
import com.example.githubdemo.ui.theme.GitHubAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Launcher activity. Kept light and simple.
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
        setContent {
            GitHubAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    GitHubApp()
                }
            }
        }
    }

    private fun enableStrictMode() {
        // Enable strict mode to catch disk and network access on the main thread.
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        // Enable strict mode to catch leaked objects.
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .build()
        )
    }
}