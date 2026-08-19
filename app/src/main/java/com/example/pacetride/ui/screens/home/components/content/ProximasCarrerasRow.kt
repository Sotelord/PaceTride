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
import com.example.pacetride.R

@Composable
fun ProximasCarrerasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RaceCard(R.drawable.running, "Carrera 10K Bogotá", "$95.000 COP", "10K",onClick = { Log.d("HomeScreen", "Race Card Clicked")}, modifier = Modifier.weight(1f))
        RaceCard(R.drawable.running, "Carrera 5K Universitaria", "$65.000 COP", "5K",onClick = { Log.d("HomeScreen", "Race Card Clicked")}, modifier = Modifier.weight(1f))
        RaceCard(R.drawable.running, "Media Maratón Bogotá", "$145.000 COP", "21K",onClick = { Log.d("HomeScreen", "Race Card Clicked")}, modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun ProximasCarrerasRowPreview(modifier: Modifier = Modifier){
    ProximasCarrerasRow()
}