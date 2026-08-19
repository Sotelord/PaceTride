package com.example.pacetride.ui.screens.explorer.components.searchBar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BusquedaYFiltros(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BarraBusqueda(modifier = Modifier.weight(1f))
        BotonFiltros(onClick = { Log.d("ExploreScreen", "Boton filtros clicked")})
    }
}

@Composable
@Preview
fun BusquedaYFiltrosPreview(modifier: Modifier = Modifier){
    BusquedaYFiltros()
}