package com.example.pacetride.ui.screens.mapaResenas.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ResenasBottomSheetContent(
    resenas: List<Resena>,
    resenaSeleccionadaId: String?,
    isLoading: Boolean,
    errorMessage: String?,
    onResenaClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Reseñas de las últimas 24 h (${resenas.size})",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        when {
            isLoading -> Box(
                Modifier.fillMaxWidth().padding(24.dp),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            errorMessage != null -> Text(
                text = errorMessage,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.error
            )

            resenas.isEmpty() -> Text(
                text = "No hay reseñas con ubicación en las últimas 24 horas",
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            else -> LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(resenas, key = { it.id }) { resena ->
                    ResenaMapaItem(
                        resena = resena,
                        seleccionada = resena.id == resenaSeleccionadaId,
                        onClick = { onResenaClick(resena.id) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ResenasBottomSheetContentPreview() {
    PacetrideTheme(darkTheme = true) {
        ResenasBottomSheetContent(
            resenas = LocalResenaProvider.listaResenas,
            resenaSeleccionadaId = null,
            isLoading = false,
            errorMessage = null,
            onResenaClick = {}
        )
    }
}

@Composable
@Preview
fun ResenasBottomSheetContentPreview2() {
    PacetrideTheme(darkTheme = false) {
        ResenasBottomSheetContent(
            resenas = LocalResenaProvider.listaResenas,
            resenaSeleccionadaId = null,
            isLoading = false,
            errorMessage = null,
            onResenaClick = {}
        )
    }
}