package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DistanciasDetalleRow(
    distancias: List<String>,
    seleccionada: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        distancias.forEach { dist ->
            ChipDistanciaDetalle(
                texto = dist,
                seleccionado = dist == seleccionada,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DistanciasDetalleRowPreview(modifier: Modifier = Modifier){
    DistanciasDetalleRow(
        listOf("5K", "10K", "21K"),
        "21K"
    )
}