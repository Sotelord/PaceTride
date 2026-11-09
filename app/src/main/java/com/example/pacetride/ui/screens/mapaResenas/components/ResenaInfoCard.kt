package com.example.pacetride.ui.screens.mapaResenas.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ResenaInfoCard(
    resena: Resena,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row (
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text (
                    text = resena.usuario ?: resena.userName ?: "Usuario",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = resena.carrera ?: "Carrera",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "★ ${resena.calificacion}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@Composable
@Preview
fun ResenaInfoCardPreview() {
    val resena = LocalResenaProvider.listaResenas[0]
    PacetrideTheme(darkTheme = true) {
        ResenaInfoCard(resena = resena)
    }
}

@Composable
@Preview
fun ResenaInfoCardPreview2() {
    val resena = LocalResenaProvider.listaResenas[0]
    PacetrideTheme(darkTheme = false) {
        ResenaInfoCard(resena = resena)
    }
}