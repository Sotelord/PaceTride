package com.example.pacetride.ui.screens.publicProfile.components.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.CarreraRealizada
import com.example.pacetride.data.local.LocalCarreraRealizadaProvider

@Composable
fun MisCarrerasRow(historialCarrera: List<CarreraRealizada>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(end = 20.dp) // Para que no se corte feo al final
    ) {
        items(historialCarrera){carreraRealizada ->
            val detalle = "${carreraRealizada.carrera.distanciaMostrable} - ${carreraRealizada.tiempo}"
            CarreraCard(carreraRealizada.carrera.nombre, detalle)
        }
    }
}

@Composable
@Preview
fun MisCarrerasRowPreview(){
    val historialCarrera = LocalCarreraRealizadaProvider.historial
    MisCarrerasRow(historialCarrera)
}
