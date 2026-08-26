package com.example.pacetride.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.screens.notifications.components.item.NotificationItem
import com.example.pacetride.ui.screens.notifications.components.header.NotificationsHeader
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import com.example.pacetride.data.Notificacion
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.data.local.LocalNotificacionProvider
import java.time.LocalDate
import java.time.LocalDateTime

// ---------- CONTENIDO ----------

@Composable
fun NotificationsScreenContent(
    notificacionesIniciales: List<Notificacion>,
    atrasPressed: () -> Unit,
    viewProfile: (Int) -> Unit,
    verCarrera: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val notificaciones = remember { notificacionesIniciales.toMutableStateList() }

    fun marcarComoLeida(id: Int) {
        val index = notificaciones.indexOfFirst { it.id == id }
        if (index != -1 && !notificaciones[index].leida) {
            notificaciones[index] = notificaciones[index].copy(leida = true)
        }
    }

    fun marcarTodasComoLeidas() {
        for (i in notificaciones.indices) {
            if (!notificaciones[i].leida) {
                notificaciones[i] = notificaciones[i].copy(leida = true)
            }
        }
    }

    val ahora = LocalDateTime.now()
    val hoy = LocalDate.now()
    val haceSieteDias = ahora.minusDays(7)

    val notificacionesHoy = notificaciones.filter {
        it.fecha.toLocalDate() == hoy
    }.sortedByDescending { it.fecha }

    val notificacionEstaSemana = notificaciones.filter {
        it.fecha.toLocalDate() != hoy && it.fecha.isAfter(haceSieteDias)
    }.sortedByDescending { it.fecha }

    val notificacionesAnteriores = notificaciones.filter {
        it.fecha.isBefore(haceSieteDias)
    }.sortedByDescending { it.fecha }

    Column(modifier = modifier) {
        NotificationsHeader(
            atrasPressed = atrasPressed,
            marcarTodasLeidasPressed = { marcarTodasComoLeidas() }
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (notificacionesHoy.isNotEmpty()) {
                item {
                    Text(
                        text = "Hoy",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                items(notificacionesHoy, key = { it.id }) { notificacion ->
                    NotificationItem(
                        notificacion = notificacion,
                        viewProfile = viewProfile,
                        verCarrera = verCarrera,
                        onClick = { marcarComoLeida(notificacion.id) }
                    )
                }
            }

            if (notificacionEstaSemana.isNotEmpty()) {
                item {
                    Text(
                        text = "Esta semana",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                    )
                }
                items(notificacionEstaSemana, key = { it.id }) { notificacion ->
                    NotificationItem(
                        notificacion = notificacion,
                        viewProfile = viewProfile,
                        verCarrera = verCarrera,
                        onClick = { marcarComoLeida(notificacion.id) }
                    )
                }
            }

            if (notificacionesAnteriores.isNotEmpty()) {
                item {
                    Text(
                        text = "Anteriores",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                    )
                }
                items(notificacionesAnteriores, key = { it.id }) { notificacion ->
                    NotificationItem(
                        notificacion = notificacion,
                        viewProfile = viewProfile,
                        verCarrera = verCarrera,
                        onClick = { marcarComoLeida(notificacion.id) }
                    )
                }
            }
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun NotificationsScreen(
    notificaciones: List<Notificacion>,
    atrasPressed: () -> Unit,
    viewProfile: (Int) -> Unit,
    verCarrera: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NotificationsScreenContent(
            notificacionesIniciales = notificaciones,
            atrasPressed = atrasPressed,
            viewProfile = viewProfile,
            verCarrera = verCarrera
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NotificationsScreenPreview() {
    val notificaciones = LocalNotificacionProvider.notificaciones
    PacetrideTheme(darkTheme = true) {
        NotificationsScreen(notificaciones, {}, {})
    }
}