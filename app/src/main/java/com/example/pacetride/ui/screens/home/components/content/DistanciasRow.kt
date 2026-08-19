package com.example.pacetride.ui.screens.home.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DistanciasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DistanciaChip("5K",onClick = { Log.d("HomeScreen", "Chip distancia clicked")}, modifier = Modifier.weight(1f))
        DistanciaChip("10K",onClick = { Log.d("HomeScreen", "Chip distancia clicked")}, modifier = Modifier.weight(1f))
        DistanciaChip("21K",onClick = { Log.d("HomeScreen", "Chip distancia clicked")}, modifier = Modifier.weight(1f))
        DistanciaChip("42K",onClick = { Log.d("HomeScreen", "Chip distancia clicked")}, modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun DistanciasRowPreview(modifier: Modifier = Modifier){
    DistanciasRow()
}