package com.example.pacetride.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.home.components.HomeScreenContent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    verCarreraButtonPressed: (Int) -> Unit,
    raceCardPressed: (Int) -> Unit,
    notificacionButtonPressed: (Int)-> Unit,
    distanciaShortcutPressed: (Int)-> Unit,
    modifier: Modifier = Modifier
) {
    val state by homeViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeScreenContent(
            state.usuario,
            state.featuredRace,
            raceCardPressed =  raceCardPressed,
            verCarreraButtonPressed = verCarreraButtonPressed,
            notificacionButtonPressed = notificacionButtonPressed,
            distanciaShortcutPressed =  distanciaShortcutPressed,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun HomeScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        HomeScreen(
            verCarreraButtonPressed = {},
            raceCardPressed = {},
            notificacionButtonPressed = {},
            distanciaShortcutPressed = {},
            homeViewModel = viewModel()
        )
    }
}