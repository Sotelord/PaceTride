package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

// "¿En cuánto la terminas?": el usuario mueve el slider de ritmo (min/km)
// y ve su tiempo final estimado para la distancia seleccionada.
@Composable
fun PredictorTiempo(
    km: Int,
    modifier: Modifier = Modifier
) {
    if (km <= 0) return

    var ritmoSeg by rememberSaveable { mutableFloatStateOf(360f) } // 6:00 min/km
    val ritmoRedondeado = (ritmoSeg / 5).roundToInt() * 5          // saltos de 5 s
    val totalSeg = ritmoRedondeado * km
    val totalAnimado by animateIntAsState(targetValue = totalSeg, label = "tiempoTotal")

    // Hora de llegada si la salida es a las 6:00 a. m.
    val llegadaSeg = 6 * 3600 + totalSeg

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = "Tu tiempo estimado en $km K",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )
        Text(
            text = totalAnimado.aTiempo(),
            color = MaterialTheme.colorScheme.primaryContainer,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ritmo",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${ritmoRedondeado.aRitmo()} min/km",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Slider(
            value = ritmoSeg,
            onValueChange = { ritmoSeg = it },
            valueRange = 180f..600f, // de 3:00 a 10:00 min/km
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primaryContainer,
                activeTrackColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        Text(
            text = "Si sales a las 6:00 a. m., llegas a las ${llegadaSeg.aHoraDelDia()}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
        )
    }
}
@Composable
@Preview
fun PredictorTiempoPreview() {
    PredictorTiempo(km = 25_800)
}

// 3725 -> "1:02:05"   |   845 -> "14:05"
private fun Int.aTiempo(): String {
    val h = this / 3600
    val m = (this % 3600) / 60
    val s = this % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, s) else "%d:%02d".format(m, s)
}

// 365 -> "6:05"
private fun Int.aRitmo(): String = "%d:%02d".format(this / 60, this % 60)

// 25_800 -> "7:10 a. m."
private fun Int.aHoraDelDia(): String {
    val h24 = (this / 3600) % 24
    val m = (this % 3600) / 60
    val sufijo = if (h24 < 12) "a. m." else "p. m."
    val h12 = if (h24 % 12 == 0) 12 else h24 % 12
    return "%d:%02d %s".format(h12, m, sufijo)
}