package com.example.pacetride.ui.screens.mapaResenas.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ResenaMapaItem(
    resena: Resena,
    seleccionada: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (seleccionada)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = resena.usuario ?: resena.userName ?: "Usuario",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (seleccionada) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "★ ${resena.calificacion}",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (seleccionada) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primaryContainer
                )
            }
            Text(
                text = resena.carrera ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = if (seleccionada) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = resena.resena,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = if (seleccionada) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = resena.fechaPublicacion,
                style = MaterialTheme.typography.labelSmall,
                color = if (seleccionada) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
fun ResenaMapaItemPreview() {
    PacetrideTheme(darkTheme = true) {
        ResenaMapaItem(
            resena = LocalResenaProvider.listaResenas[0],
            seleccionada = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ResenaMapaItemPreview2() {
    PacetrideTheme(darkTheme = true) {
        ResenaMapaItem(
            resena = LocalResenaProvider.listaResenas[0],
            seleccionada = false,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ResenaMapaItemPreview3() {
    PacetrideTheme(darkTheme = false) {
        ResenaMapaItem(
            resena = LocalResenaProvider.listaResenas[0],
            seleccionada = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ResenaMapaItemPreview4() {
    PacetrideTheme(darkTheme = false) {
        ResenaMapaItem(
            resena = LocalResenaProvider.listaResenas[0],
            seleccionada = false,
            onClick = {}
        )
    }
}