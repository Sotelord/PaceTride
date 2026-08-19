package com.example.pacetride.ui.screens.profile.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.CarreraHistorial
import com.example.pacetride.data.local.LocalCarreraHistorialProvider

@Composable
fun HistorialRow(carreras: List<CarreraHistorial>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) { carrera ->
            HistoryRaceCard(carrera.titulo, carrera.resultado, carrera.fecha)
        }
    }
}

@Composable
@Preview
fun HistorialRowPreview(modifier: Modifier = Modifier){
    HistorialRow(LocalCarreraHistorialProvider.historial)
}