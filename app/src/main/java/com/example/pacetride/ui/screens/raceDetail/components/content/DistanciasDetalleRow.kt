package com.example.pacetride.ui.screens.raceDetail.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DistanciasDetalleRow(
    distancias: List<String>,
    seleccionada: String,
    onSeleccionar: (String) -> Unit,
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
                onClick = {
                    Log.d("RaceDetailScreen", "ChipDistancia clicked")
                    onSeleccionar(dist)
                          },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DistanciasDetalleRowInteractivoPreview(modifier: Modifier = Modifier){
    var seleccionada by remember { mutableStateOf("21K") }

    DistanciasDetalleRow(
        distancias = listOf("5K", "10K", "21K"),
        seleccionada = seleccionada,
        onSeleccionar = { seleccionada = it }
    )
}