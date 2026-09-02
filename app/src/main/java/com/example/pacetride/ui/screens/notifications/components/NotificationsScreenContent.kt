package com.example.pacetride.ui.screens.notifications.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.data.Notificacion
import com.example.pacetride.data.local.LocalNotificacionProvider
import com.example.pacetride.ui.screens.notifications.components.header.NotificationsHeader
import com.example.pacetride.ui.screens.notifications.components.item.NotificationItem
import com.example.pacetride.ui.theme.PacetrideTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun NotificationsScreenContent(
    notificaciones: List<Notificacion>,
    onMarcarComoLeida: (Int) -> Unit,
    onMarcarTodasComoLeidas: () -> Unit,
    atrasPressed: () -> Unit,
    viewProfile: (Int) -> Unit,
    verCarrera: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val ahora = LocalDateTime.now()
    val hoy = LocalDate.now()
    val haceSieteDias = ahora.minusDays(7)

    val notificacionesHoy = remember(notificaciones) {
        notificaciones.filter { it.fecha.toLocalDate() == hoy }
            .sortedByDescending { it.fecha }
    }

    val notificacionEstaSemana = remember(notificaciones) {
        notificaciones.filter {
            it.fecha.toLocalDate() != hoy && it.fecha.isAfter(haceSieteDias)
        }.sortedByDescending { it.fecha }
    }

    val notificacionesAnteriores = remember(notificaciones) {
        notificaciones.filter { it.fecha.isBefore(haceSieteDias) }
            .sortedByDescending { it.fecha }
    }

    Column(modifier = modifier) {
        NotificationsHeader(
            atrasPressed = atrasPressed,
            marcarTodasLeidasPressed = onMarcarTodasComoLeidas
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
                        onClick = { onMarcarComoLeida(notificacion.id) }
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
                        onClick = { onMarcarComoLeida(notificacion.id) }
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
                        onClick = { onMarcarComoLeida(notificacion.id) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview (showBackground = true)
fun MotificationsScreenContentPreview(){
    val notificaciones = LocalNotificacionProvider.notificaciones
    PacetrideTheme(darkTheme = true) {
        NotificationsScreenContent(
            atrasPressed = {},
            onMarcarComoLeida = {},
            notificaciones = notificaciones,
            onMarcarTodasComoLeidas = {},
            verCarrera = {},
            viewProfile = {}
        )
    }
}