package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.ZoneId

// Cuenta regresiva en vivo. Espera la fecha en formato ISO ("2026-10-18"), que es lo que devuelve tu API.
// Si la fecha no se puede leer (por ejemplo "15 de agosto" de los providers locales), no dibuja nada.
@Composable
fun CuentaRegresiva(
    fechaIso: String,
    modifier: Modifier = Modifier
) {
    // La hora de salida está fija en 6:00 a. m. en la pantalla de detalle, así que usamos la misma.
    val objetivo = remember(fechaIso) {
        runCatching {
            LocalDate.parse(fechaIso)
                .atTime(6, 0)
                .atZone(ZoneId.of("America/Bogota"))
                .toInstant()
                .toEpochMilli()
        }.getOrNull()
    }
    var ahora by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            ahora = System.currentTimeMillis()
        }
    }

    if (objetivo == null) return

    val restanteSeg = (objetivo - ahora) / 1000
    if (restanteSeg <= 0) {
        Text(
            text = "Esta carrera ya se realizó",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            modifier = modifier
        )
        return
    }

    val dias = restanteSeg / 86_400
    val horas = (restanteSeg % 86_400) / 3_600
    val minutos = (restanteSeg % 3_600) / 60
    val segundos = restanteSeg % 60

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CajaTiempo(dias, "días", Modifier.weight(1f))
        CajaTiempo(horas, "horas", Modifier.weight(1f))
        CajaTiempo(minutos, "min", Modifier.weight(1f))
        CajaTiempo(segundos, "seg", Modifier.weight(1f))
    }
}

@Composable
@Preview
fun CuentaRegresivaPreview() {
    PacetrideTheme(darkTheme = true) {
        CuentaRegresiva(fechaIso = "2026-10-18")
    }
}

@Composable
@Preview
fun CuentaRegresivaSinFechaPreview() {
    PacetrideTheme(darkTheme = true) {
        CuentaRegresiva(fechaIso = "15 de agosto")
    }
}

@Composable
private fun CajaTiempo(valor: Long, etiqueta: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "%02d".format(valor),
            color = MaterialTheme.colorScheme.primaryContainer,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = etiqueta,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
        )
    }
}

@Composable
@Preview
fun CajaTiempoPreview() {
    PacetrideTheme(darkTheme = true) {
        CajaTiempo(valor = 12, etiqueta = "días")
    }
}

@Composable
@Preview
fun CajaTiempoSinValorPreview() {
    PacetrideTheme(darkTheme = true) {
        CajaTiempo(valor = 0, etiqueta = "horas")
    }
}
