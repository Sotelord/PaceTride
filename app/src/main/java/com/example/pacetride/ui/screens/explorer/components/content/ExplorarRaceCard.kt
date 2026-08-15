package com.example.pacetride.ui.screens.explorer.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.AppButton

@Composable
fun ExplorarRaceCard(
    idImagen: Int,
    titulo: String,
    ubicacion: String,
    fecha: String,
    distancia: String,
    precio: String? = null,
    ultimosCupos: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "$ubicacion • $fecha",
                color = Color.LightGray,
                fontSize = 12.sp
            )

            if (ultimosCupos) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    stringResource(R.string.ultimos_cupos),
                    color = colorResource(R.color.pulse_orange),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            } else if (precio != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "$distancia • $precio",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                AppButton(
                    textoBoton = stringResource(R.string.ver_detalles),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview
fun ExplorarRaceCardPreview(modifier: Modifier = Modifier){
    ExplorarRaceCard(
        R.drawable.running,
        "Carrera Atlética Bogotá 10K",
        "Bogotá",
        "15 de agosto",
        "10K",
        "$90.000 COP"
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview2(modifier: Modifier = Modifier){
    ExplorarRaceCard(
        R.drawable.running,
        "Corre por Bogotá 5K",
        "Bogotá",
        "30 de agosto",
        "5K",
        "$65.000 COP"
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview3(modifier: Modifier = Modifier){
    ExplorarRaceCard(
        R.drawable.running,
        "Media Maratón Bogotá 2026",
        "Bogotá",
        "27 de septiembre",
        "21K",
        "$145.000 COP"
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview4(modifier: Modifier = Modifier){
    ExplorarRaceCard(
        R.drawable.running,
        "Correra 0 Bogotá",
        "Bogotá",
        "27 de agosto",
        "",
        ultimosCupos = true
    )
}