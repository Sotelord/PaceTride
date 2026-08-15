package com.example.pacetride.ui.screens.profile.components.estadisticas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.R

@Composable
fun EstadisticasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatBox(R.drawable.ic_medalla, "12", stringResource(R.string.carreras))
        StatBox(R.drawable.ic_distancia, "186,4 km", stringResource(R.string.distancia))
        StatBox(R.drawable.ic_cronometro, "48:32", stringResource(R.string.mejor_10k))
        StatBox(R.drawable.ic_cronometro, "1:52:40", stringResource(R.string.mejor_21k))
    }
}

@Composable
@Preview(showBackground = true)
fun EstadisiticasRowPreview(modifier: Modifier = Modifier){
    EstadisticasRow()
}