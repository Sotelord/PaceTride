package com.example.pacetride.ui.screens.profile.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun HistoryRaceCard(
    titulo: String,
    resultado: String,
    fecha: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.graphite))
            .padding(12.dp)
    ) {
        Text(
            titulo,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(resultado, color = Color.LightGray, fontSize = 12.sp)
        Text(fecha, color = Color.LightGray, fontSize = 10.sp)
    }
}

@Composable
@Preview
fun HistoryRaceCardPreview(modifier: Modifier = Modifier){
    HistoryRaceCard(
        "Carrera 10K Bogotá",
        "10K • 49:12",
        "15 de junio de 2026"
    )
}

@Composable
@Preview
fun HistoryRaceCardPreview2(modifier: Modifier = Modifier){
    HistoryRaceCard(
        "Carrera Universitaria Javeriana",
        "5K • 23:48",
        "20 de mayo de 2026"
    )
}

@Composable
@Preview
fun HistoryRaceCardPreview3(modifier: Modifier = Modifier){
    HistoryRaceCard(
        "Bogotá Night Run",
        "10K • 50:31",
        "18 de abril de 2026"
    )
}