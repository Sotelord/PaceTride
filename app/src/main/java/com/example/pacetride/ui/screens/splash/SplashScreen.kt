package com.example.pacetride.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    splashViewModel: SplashViewModel
){
    val navigateHome by splashViewModel.navigateHome.collectAsState()

    if (navigateHome) {
        navigateToHome()
    } else {
        navigateToLogin()
    }
}