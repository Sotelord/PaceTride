package com.example.pacetride.ui.screens.profile.components.estadisticas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun NextRaceCardWithGraph(
    titulo: String,
    fecha: String,
    distancia: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
    ) {
        GraficoProgreso(modifier = Modifier.fillMaxSize())

        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("$fecha • $distancia", color = Color.LightGray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.inscrito),
                    color = colorResource(R.color.electric_lime),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun NextRaceCardWithGraphPreview(modifier: Modifier = Modifier){
    NextRaceCardWithGraph(
        "Media Maratón Bogotá 2026",
        "27 de septiembre",
        "21K"
    )
}