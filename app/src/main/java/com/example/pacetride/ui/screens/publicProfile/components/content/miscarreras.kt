package com.example.pacetride.ui.screens.publicprofile.components.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.publicprofile.components.content.CarreraCard

@Composable
fun MisCarrerasRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(end = 20.dp) // Para que no se corte feo al final
    ) {
        item {
            CarreraCard(titulo = "Media Maratón Bogotá", detalle = "21K - 1:48:52")
        }
        item {
            CarreraCard(titulo = "Carrera 10K Bogotá", detalle = "10K - 47:35")
        }
    }
}

@Composable
@Preview
fun MisCarrerasRowPreview(modifier: Modifier = Modifier){
    MisCarrerasRow()
}
