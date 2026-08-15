package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- SECCIÓN CON TÍTULO SIMPLE (18sp, sin padding extra) ----------

@Composable
fun TituloSeccionDetalle(texto: String, modifier: Modifier = Modifier) {
    Text(texto, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = modifier)
}

@Composable
@Preview
fun TituloSeccionDetallePreview(modifier: Modifier = Modifier){
    TituloSeccionDetalle(stringResource(R.string.sobre_la_carrera))
}

@Composable
@Preview
fun TituloSeccionDetallePreview2(modifier: Modifier = Modifier){
    TituloSeccionDetalle(stringResource(R.string.ruta))
}

@Composable
@Preview
fun TituloSeccionDetallePreview3(modifier: Modifier = Modifier){
    TituloSeccionDetalle(stringResource(R.string.tu_inscripcion_incluye))
}