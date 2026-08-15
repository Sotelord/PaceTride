package com.example.pacetride.ui.screens.explorer.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.explorer.model.CarreraExplorar
import kotlin.collections.listOf

@Composable
fun GrillaCarreras(
    carreras: List<CarreraExplorar>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) { carrera ->
            ExplorarRaceCard(
                idImagen = carrera.idImagen,
                titulo = carrera.titulo,
                ubicacion = carrera.ubicacion,
                fecha = carrera.fecha,
                distancia = carrera.distancia,
                precio = carrera.precio,
                ultimosCupos = carrera.ultimosCupos
            )
        }
    }
}

@Composable
@Preview
fun GrillaCarrerasPreview(modifier: Modifier = Modifier){
    val carreras = listOf(
        CarreraExplorar(
            R.drawable.running,
            "Carrera Atlética Bogotá 10K",
            "Bogotá",
            "15 de agosto",
            "10K",
            "$90.000 COP"
        ),
        CarreraExplorar(
            R.drawable.running,
            "Corre por Bogotá 5K",
            "Bogotá",
            "30 de agosto",
            "5K",
            "$65.000 COP"
        ),
        CarreraExplorar(
            R.drawable.running,
            "Media Maratón Bogotá 2026",
            "Bogotá",
            "27 de septiembre",
            "21K",
            "$145.000 COP"
        ),
        CarreraExplorar(
            R.drawable.running,
            "Correra 0 Bogotá",
            "Bogotá",
            "27 de agosto",
            "",
            ultimosCupos = true
        )
    )
    GrillaCarreras(carreras = carreras)
}