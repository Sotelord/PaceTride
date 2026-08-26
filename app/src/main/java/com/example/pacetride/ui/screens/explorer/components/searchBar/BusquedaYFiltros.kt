package com.example.pacetride.ui.screens.explorer.components.searchBar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BusquedaYFiltros(
    textoBusqueda: String,
    onTextoBusquedaChange: (String) -> Unit,
    filtrosVisibles: Boolean,
    onToggleFiltros: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BarraBusqueda(
            texto = textoBusqueda,
            onTextoChange = onTextoBusquedaChange,
            modifier = Modifier.weight(1f)
        )
        BotonFiltros(
            activo = filtrosVisibles,
            onClick = {
                Log.d("ExploreScreen", "Boton filtros clicked, visible=${!filtrosVisibles}")
                onToggleFiltros()
            }
        )
    }
}

@Composable
@Preview
fun BusquedaYFiltrosPreview() {
    var texto by remember { mutableStateOf("") }
    var filtrosVisibles by remember { mutableStateOf(false) }
    BusquedaYFiltros(
        textoBusqueda = texto,
        onTextoBusquedaChange = { texto = it },
        filtrosVisibles = filtrosVisibles,
        onToggleFiltros = { filtrosVisibles = !filtrosVisibles }
    )
}