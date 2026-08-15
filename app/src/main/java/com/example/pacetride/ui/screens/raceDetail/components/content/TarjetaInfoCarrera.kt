package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

// ---------- ITEM DE INFO (ícono + etiqueta + valor) ----------

@Composable
fun TarjetaInfoCarrera(
    fecha: String,
    hora: String,
    lugar: String,
    distancia: String,
    precio: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoItem(R.drawable.ic_calendario,
                stringResource(R.string.fecha), fecha, Modifier.weight(1f))
            InfoItem(R.drawable.ic_reloj,
                stringResource(R.string.hora_de_salida), hora, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoItem(R.drawable.ubicacion_blanco,
                stringResource(R.string.lugar), lugar, Modifier.weight(1f))
            InfoItem(R.drawable.ic_distancia, stringResource(R.string.distancia), distancia, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InfoItem(R.drawable.ic_dinero,
            stringResource(R.string.inscripcion), precio, Modifier.fillMaxWidth())
    }
}

@Composable
@Preview
fun TarjetaInfoCarreraPreview(modifier: Modifier = Modifier){
    TarjetaInfoCarrera(
        "27 de septiembre de 2026",
        "6:00 a. m.",
        "Bogotá, Colombia",
        "21 kilómetros",
        "$145.000 COP"
    )
}