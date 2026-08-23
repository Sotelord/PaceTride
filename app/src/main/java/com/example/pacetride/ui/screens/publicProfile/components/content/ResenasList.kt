package com.example.pacetride.ui.screens.publicprofile.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.screens.publicprofile.components.content.ResenaCard

@Composable
fun ResenasList(resenas: List<Resena>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp), // Espacio entre cada tarjeta
        modifier = modifier.fillMaxWidth()
    ) {
        resenas.forEach { itemResena ->
            ResenaCard(resena = itemResena)
        }
    }
}

@Composable
@Preview
fun ResenasListPreview(modifier: Modifier = Modifier) {
    ResenasList(resenas = LocalResenaProvider.listaResenas)
}