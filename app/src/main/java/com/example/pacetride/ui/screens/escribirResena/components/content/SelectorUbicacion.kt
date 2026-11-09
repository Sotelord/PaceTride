package com.example.pacetride.ui.screens.escribirResena.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun SelectorUbicacion(
    compartirUbicacion: Boolean,
    errorUbicacion: String?,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Compartir ubicación en el mapa",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Tu reseña aparecerá en el mapa durante 24 horas",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = compartirUbicacion,
                onCheckedChange = onToggle
            )
        }
        if (errorUbicacion != null) {
            Text(
                text = errorUbicacion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectorUbicacionPreview() {
    PacetrideTheme(darkTheme = true) {
        SelectorUbicacion(compartirUbicacion = false, errorUbicacion = null, onToggle = {})
    }
}