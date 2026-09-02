package com.example.pacetride.ui.screens.misCarreras

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.screens.misCarreras.components.MisCarrerasScreenContent
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun MisCarrerasScreen(
    misCarrerasViewModel: MisCarrerasViewModel,
    notificacionButtonPressed: (Int) -> Unit,
    verCarreraButtonPressed: (Int) -> Unit,
    explorarPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by misCarrerasViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MisCarrerasScreenContent(
            proximas = state.proximas,
            completadas = state.completadas,
            selectedTabIndex = state.selectedTabIndex,
            onTabSelected = { misCarrerasViewModel.updateSelectedTabIndex(it) },
            notificacionButtonPressed = { notificacionButtonPressed(state.usuarioId) },
            verCarreraButtonPressed = verCarreraButtonPressed,
            explorarPressed = explorarPressed
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E1011)
@Composable
fun MisCarrerasScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        MisCarrerasScreen(
            misCarrerasViewModel = viewModel(),
            notificacionButtonPressed = {},
            verCarreraButtonPressed = {},
            explorarPressed = {}
        )
    }
}