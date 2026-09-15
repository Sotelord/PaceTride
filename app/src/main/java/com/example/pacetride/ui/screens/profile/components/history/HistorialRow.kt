package com.example.pacetride.ui.screens.profile.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.CarreraRealizada
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun HistorialRow(carreras: List<CarreraRealizada>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) { carreraRealizada ->
            HistoryRaceCard(
                carreraRealizada.carrera.nombre,
                "${carreraRealizada.carrera.ubicacion} • ${carreraRealizada.tiempo}",
                carreraRealizada.carrera.fecha)
        }
    }
}

@Composable
@Preview
fun HistorialRowPreview(){
    PacetrideTheme(darkTheme = true) {
        HistorialRow(
            carreras =  LocalUsuarioProvider.usuarios[0].historial
        )
    }
}