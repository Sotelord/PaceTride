package com.example.pacetride.ui.screens.profile.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme

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
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            titulo,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis, 
            lineHeight = 16.sp
        )
        Column {
            Text(
                resultado,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                fecha,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun HistoryRaceCardPreview() {
    PacetrideTheme(darkTheme = true) {
        HistoryRaceCard(
            titulo =  "Carrera 10K Bogotá",
            resultado =  "10K • 49:12",
            fecha =  "15 de junio de 2026"
        )
    }
}

@Composable
@Preview
fun HistoryRaceCardPreview2() {
    PacetrideTheme(darkTheme = true) {
        HistoryRaceCard(
            titulo = "Carrera Universitaria Javeriana",
            resultado = "5K • 23:48",
            fecha = "20 de mayo de 2026"
        )
    }
}

@Composable
@Preview
fun HistoryRaceCardPreview3() {
    PacetrideTheme(darkTheme = true) {
        HistoryRaceCard(
            titulo = "Bogotá Night Run",
            resultado = "10K • 50:31",
            fecha = "18 de abril de 2026"
        )
    }
}