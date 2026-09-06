package com.example.pacetride.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

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