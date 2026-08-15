package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TituloCarrera(
    titulo: String,
    fecha: String,
    ubicacion: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(titulo, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(fecha, color = Color.LightGray, fontSize = 14.sp)
        Text(ubicacion, color = Color.LightGray, fontSize = 14.sp)
    }
}

@Composable
@Preview
fun TituloCarreraPreview(modifier: Modifier = Modifier){
    TituloCarrera("Media Maratón Bogotá 2026",
        "27 de septiembre de 2026",
        "Bogotá, Colombia"
    )
}