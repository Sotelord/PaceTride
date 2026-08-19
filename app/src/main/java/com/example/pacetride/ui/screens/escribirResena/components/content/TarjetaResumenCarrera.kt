package com.example.pacetride.ui.screens.escribirResena.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun TarjetaResumenCarrera(
    titulo: String,
    fecha: String,
    ubicacion: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.corriendo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(R.color.electric_lime)),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(titulo, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(fecha, color = Color.LightGray, fontSize = 13.sp)
            Text(ubicacion, color = Color.LightGray, fontSize = 13.sp)
        }
    }
}

@Composable
@Preview
fun TarjetaResumenCarreraPreview(modifier: Modifier = Modifier){
    TarjetaResumenCarrera(
        "Media Maratón Bogotá 2026",
        "27 de septiembre de 2026",
        "Bogotá, Colombia"
    )
}