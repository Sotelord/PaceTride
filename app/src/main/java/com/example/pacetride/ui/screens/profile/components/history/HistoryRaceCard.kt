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
            .height(120.dp) // 👈 altura fija, así todas miden lo mismo
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween // 👈 título arriba, resultado/fecha abajo, sin importar cuánto ocupe el título
    ) {
        Text(
            titulo,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis, // 👈 protege contra nombres largos tipo "Carrera Universitaria Javeriana"
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
fun HistoryRaceCardPreview(modifier: Modifier = Modifier) {
    HistoryRaceCard(
        "Carrera 10K Bogotá",
        "10K • 49:12",
        "15 de junio de 2026"
    )
}

@Composable
@Preview
fun HistoryRaceCardPreview2(modifier: Modifier = Modifier) {
    HistoryRaceCard(
        "Carrera Universitaria Javeriana",
        "5K • 23:48",
        "20 de mayo de 2026"
    )
}

@Composable
@Preview
fun HistoryRaceCardPreview3(modifier: Modifier = Modifier) {
    HistoryRaceCard(
        "Bogotá Night Run",
        "10K • 50:31",
        "18 de abril de 2026"
    )
}