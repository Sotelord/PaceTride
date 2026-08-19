package com.example.pacetride.ui.screens.explorer.components.searchBar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FiltrosRow(modifier: Modifier = Modifier) {
    val filtros = listOf("Todas", "5K", "10K", "21K", "42K")
    var seleccionado by remember { mutableStateOf("Todas") }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filtros) { filtro ->
            FiltroChip(
                texto = filtro,
                seleccionado = filtro == seleccionado,
                onClick = {
                    Log.d("ExploreScreen", "Chip filtro clicked")
                    seleccionado = filtro
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FiltrosRowPreview(modifier: Modifier = Modifier){
    FiltrosRow()
}