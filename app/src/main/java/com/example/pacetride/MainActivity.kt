package com.example.pacetride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pacetride.ui.theme.PacetrideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// Donde inicia la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PacetrideTheme {
                PacetrideApp()
            }
        }
    }
}