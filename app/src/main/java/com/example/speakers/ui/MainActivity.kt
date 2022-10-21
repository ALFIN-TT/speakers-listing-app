package com.example.speakers.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speakers.ui.navigation.Screen
import com.example.speakers.ui.screen.speakers.SpeakersScreen
import com.example.speakers.ui.theme.SpeakersTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set FLAG_LAYOUT_NO_LIMITS
        // app content will extend to system bars
        // it should be before setContent block
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            SpeakersTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    // set transparent color so that our image is visible under the status bar
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SpeakersScreen.route
                    ) {
                        composable(route = Screen.SpeakersScreen.route) {
                            SpeakersScreen(navHostController = navController) {
                                onBackPressed()
                            }
                        }
                    }
                }
            }
        }
    }
}
