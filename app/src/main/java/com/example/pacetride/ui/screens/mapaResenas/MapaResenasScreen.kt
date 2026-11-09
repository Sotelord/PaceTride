package com.example.pacetride.ui.screens.mapaResenas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.screens.mapaResenas.components.MapaResenasScreenContent
import com.example.pacetride.ui.theme.PacetrideTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapaResenasScreen(
    mapaResenasViewModel: MapaResenasViewModel,
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    val state by mapaResenasViewModel.uiState.collectAsState()

    LaunchedEffect(Unit){
        mapaResenasViewModel.observeResenasRecientes()
    }

    MapaResenasScreenContent(
        resenas = state.resenas,
        resenaSeleccionadaId = state.resenaSeleccionadaId,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        onResenaSeleccionada = { mapaResenasViewModel.seleccionarResena(it) },
        onMapaClick = { mapaResenasViewModel.limpiarSeleccion() },
        atrasPressed = atrasPressed,
        modifier = modifier
    )
}

@Preview
@Composable
fun MapaResenasScreenPreview(){
    PacetrideTheme(darkTheme = true) {
        MapaResenasScreen(
            mapaResenasViewModel = viewModel(),
            atrasPressed = {}
        )
    }
}