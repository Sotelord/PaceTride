package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.data.Sesion
import com.example.pacetride.ui.theme.PacetrideTheme
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import kotlin.math.ceil
import kotlin.math.roundToInt

private val NIVELES = listOf("Principiante", "Intermedio", "Avanzado")
private val FACTOR_NIVEL = listOf(0.8, 1.0, 1.15)


@Composable
fun PlanEntrenamiento(
    km: Int,
    fechaIso: String,
    modifier: Modifier = Modifier
) {
    val semanas = remember(fechaIso) {
        val fecha = runCatching { LocalDate.parse(fechaIso) }.getOrNull()
        if (fecha == null) {
            8
        } else {
            val dias = ChronoUnit.DAYS.between(
                LocalDate.now(ZoneId.of("America/Bogota")), fecha
            )
            if (dias <= 0) 0 else ceil(dias / 7.0).toInt().coerceIn(1, 12)
        }
    }
    var nivel by rememberSaveable { mutableIntStateOf(1) }
    var semanaSeleccionada by rememberSaveable { mutableIntStateOf(1) }

    if (km <= 0 || semanas == 0) return

    val plan = remember(semanas, km, nivel) {
        (1..semanas).map { construirSemana(it, semanas, km, FACTOR_NIVEL[nivel]) }
    }
    val totales = plan.map { semana -> semana.sumOf { it.km } }
    val maximo = maxOf(totales.maxOrNull() ?: 1.0, 1.0)
    val sel = semanaSeleccionada.coerceIn(1, semanas)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = "Tu plan de entrenamiento",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$semanas ${if (semanas == 1) "semana" else "semanas"} para tu carrera de $km K",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Selector de nivel
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            NIVELES.forEachIndexed { i, nombre ->
                val activo = i == nivel
                Text(
                    text = nombre,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (activo) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (activo) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.15f)
                        )
                        .clickable { nivel = i }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barras de volumen por semana (tocar una barra selecciona la semana)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            totales.forEachIndexed { i, total ->
                val numero = i + 1
                val seleccionada = numero == sel
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { semanaSeleccionada = numero }
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(22.dp)
                            .height((12 + 56 * (total / maximo)).dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (seleccionada) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.35f)
                            )
                    )
                    Text(
                        text = "$numero",
                        fontSize = 11.sp,
                        color = if (seleccionada) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val sesiones = plan[sel - 1]
        val etiqueta = when {
            sel == semanas -> "Semana de carrera"
            sel % 4 == 0 -> "Semana de descanso: menos volumen para recuperar"
            else -> "Semana $sel de $semanas"
        }
        Text(
            text = "$etiqueta · ${"%.1f".format(totales[sel - 1])} km en total",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        sesiones.forEach { sesion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = sesion.dia,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(88.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${sesion.titulo} · ${"%.1f".format(sesion.km)} km",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = sesion.detalle,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PlanEntrenamientoPreview1() {
    PacetrideTheme(darkTheme = true) {
        PlanEntrenamiento(
            km = 12,
            fechaIso = LocalDate.now().toString(),
        )
    }
}

@Composable
@Preview
fun PlanEntrenamientoPreview2() {
    PacetrideTheme(darkTheme = false) {
        PlanEntrenamiento(
            km = 12,
            fechaIso = LocalDate.now().toString(),
        )
    }
}

private fun redondear(valor: Double): Double = (valor * 2).roundToInt() / 2.0

private fun construirSemana(semana: Int, total: Int, km: Int, factor: Double): List<Sesion> {
    // Última semana: baja la carga y termina con la carrera
    if (semana == total) {
        return listOf(
            Sesion("Martes", "Rodaje suave", "20 minutos muy cómodos para soltar las piernas", 3.0),
            Sesion("Jueves", "Activación", "15 min suaves + 4 aceleraciones de 20 segundos", 2.5),
            Sesion(
                "Día de carrera", "¡A competir!",
                "Sal tranquilo y acelera en la segunda mitad", km.toDouble()
            )
        )
    }

    // La tirada larga crece poco a poco hasta un pico antes de la carrera
    val progreso = if (total > 2) (semana - 1).toDouble() / (total - 2) else 1.0
    val pico = if (km <= 10) km.toDouble() else minOf(km * 0.85, 32.0)
    val inicio = maxOf(km * 0.4, 2.0)
    var larga = inicio + (pico - inicio) * progreso.coerceIn(0.0, 1.0)
    if (semana % 4 == 0) larga *= 0.75 // semana de descanso
    larga = redondear(larga * factor)

    val suave = redondear(maxOf(larga * 0.5, 2.0))
    val calidadKm = redondear(maxOf(larga * 0.6, 3.0))

    val calidad = if (semana % 2 == 1) {
        val repeticiones = minOf(4 + semana, 10)
        Sesion(
            "Jueves", "Intervalos",
            "$repeticiones × 400 m rápidos con 90 s de trote suave entre cada uno",
            calidadKm
        )
    } else {
        val minutos = minOf(15 + 2 * semana, 35)
        Sesion(
            "Jueves", "Tempo",
            "$minutos min a ritmo sostenido: exigente pero controlado",
            calidadKm
        )
    }

    return listOf(
        Sesion("Martes", "Rodaje suave", "Ritmo cómodo: debes poder conversar mientras corres", suave),
        calidad,
        Sesion("Sábado", "Tirada larga", "Constante y sin afán, lo importante es sumar kilómetros", larga)
    )
}