package com.example.pacetride.ui.screens.explorer.components.searchBar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme

val FILTROS_DISTANCIA = listOf("Todas", "5K", "10K", "21K", "42K")

@Composable
fun FiltrosRow(
    filtroSeleccionado: String,
    onFiltroSeleccionado: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(FILTROS_DISTANCIA) { filtro ->
            FiltroChip(
                texto = filtro,
                seleccionado = filtro == filtroSeleccionado,
                onClick = {
                    Log.d("ExploreScreen", "Chip filtro clicked: $filtro")
                    onFiltroSeleccionado(filtro)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FiltrosRowPreview() {
    var seleccionado by remember { mutableStateOf("Todas") }
    PacetrideTheme(darkTheme = true) {
        FiltrosRow(
            filtroSeleccionado = seleccionado,
            onFiltroSeleccionado = { seleccionado = it }
        )
    }
}