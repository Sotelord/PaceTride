package com.example.pacetride.ui.screens.profile.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.profile.model.CarreraHistorial

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
    val historial = listOf(
        CarreraHistorial("Carrera 10K Bogotá", "10K • 49:12", "15 de junio de 2026"),
        CarreraHistorial("Carrera Universitaria Javeriana", "5K • 23:48", "20 de mayo de 2026"),
        CarreraHistorial("Bogotá Night Run", "10K • 50:31", "18 de abril de 2026")
    )
    HistorialRow(historial)
}