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
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider

@Composable
fun GrillaCarreras(
    verCarreraButtonPressed: (Int)->Unit,
    carreras: List<Carrera>,
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
                verCarreraButtonPressed = verCarreraButtonPressed,
                carrera
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GrillaCarrerasPreview(){
    GrillaCarreras(
        verCarreraButtonPressed = {},
        carreras = LocalCarreraProvider.listCarrera
    )
}