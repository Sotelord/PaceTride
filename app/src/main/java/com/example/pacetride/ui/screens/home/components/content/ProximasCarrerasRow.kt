package com.example.pacetride.ui.screens.home.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider

@Composable
fun ProximasCarrerasRow(carreras: List<Carrera>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) {
            carrera -> RaceCard(carrera,onClick = { Log.d("HomeScreen", "Race Card Clicked")})

        }
    }
}

@Preview
@Composable
fun ProximasCarrerasRowPreview(modifier: Modifier = Modifier){
    val carreras = LocalCarreraProvider.listCarrera
    ProximasCarrerasRow(carreras)
}