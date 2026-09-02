package com.example.pacetride.ui.screens.raceDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.data.aPrecioCop
import com.example.pacetride.ui.screens.raceDetail.components.RaceDetailScreenContent
import com.example.pacetride.ui.screens.raceDetail.components.content.BotonInscripcion

@Composable
fun RaceDetailScreen(
    raceDatailViewModel: RaceDatailViewModel,
    raceId: Int,
    atrasPressed: () -> Unit,
    inscribemePressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by raceDatailViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        raceDatailViewModel.getRaceId(raceId)
    }

    if (state.carrera != null) {
        val precioActual = raceDatailViewModel.getPrecioActual()

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            RaceDetailScreenContent(
                carrera = state.carrera!!,
                kmSeleccionado = state.kmSeleccionado,
                onSeleccionarKm = { raceDatailViewModel.updateKmSeleccionado(it) },
                atrasPressed = atrasPressed,
                modifier = Modifier.weight(1f),
            )
            // ---------- "bottomBar" manual ----------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                BotonInscripcion(inscribemePressed, precio = precioActual.aPrecioCop())
            }
        }
    } else{
        Text("Carrera no encontrada")
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun RaceDetailScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        RaceDetailScreen(
            raceDatailViewModel = viewModel(),
            inscribemePressed = {},
            raceId = 2,
            atrasPressed = {},
        )
    }
}