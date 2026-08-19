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
import com.example.pacetride.data.CarreraExplorar
import com.example.pacetride.data.local.LocalCarreraExplorarProvider

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
    GrillaCarreras(carreras = LocalCarreraExplorarProvider.carreras)
}