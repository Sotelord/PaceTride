package com.example.pacetride.ui.utils.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.R
import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.local.LocalUsuarioProvider

@Composable
fun EstadisticasRow(estadisticasGlobales: EstadisticasGlobales, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val numCarrera = estadisticasGlobales.numCarrera.toString()
        StatBox(R.drawable.ic_medalla, numCarrera, stringResource(R.string.carreras))
        StatBox(
            R.drawable.ic_distancia,
            estadisticasGlobales.distacia,
            stringResource(R.string.distancia)
        )
        StatBox(
            R.drawable.ic_cronometro,
            estadisticasGlobales.mejorTiempo10k,
            stringResource(R.string.mejor_10k)
        )
        StatBox(
            R.drawable.ic_cronometro,
            estadisticasGlobales.mejorTiempo21k,
            stringResource(R.string.mejor_21k)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EstadisiticasRowPreview(){
    val estadisticasGlobales = LocalUsuarioProvider.usuarios[0].estadisticasGlobales
    EstadisticasRow(estadisticasGlobales)
}