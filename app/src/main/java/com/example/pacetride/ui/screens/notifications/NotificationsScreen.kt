package com.example.pacetride.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.screens.notifications.components.NotificationItem
import com.example.pacetride.ui.screens.notifications.components.NotificationsHeader
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import com.example.compose.PacetrideTheme
import com.example.pacetride.data.local.LocalNotificacionProvider
import java.time.LocalDate
import java.time.LocalDateTime

// ---------- CONTENIDO ----------

@Composable
fun NotificationsScreenContent(modifier: Modifier = Modifier) {
    val notificaciones = LocalNotificacionProvider.notificaciones

    val ahora = LocalDateTime.now()
    val hoy = LocalDate.now()
    val haceSieteDias = ahora.minusDays(7)

    //Notificaciones recibidas hoy
    val notificacionesHoy = notificaciones.filter {
        it.fecha.toLocalDate() == hoy
    }.sortedByDescending { it.fecha }

    //Notificaciones de los ultimos 7 días, excluyendo las de hoy
    val notificacionEstaSemana = notificaciones.filter {
        it.fecha.toLocalDate() != hoy && it.fecha.isAfter(haceSieteDias)
    }.sortedByDescending { it.fecha }

    //Notificaciones anteriores a los ultimos 7 dias
    val notificacionesAnteriores = notificaciones.filter {
        it.fecha.isBefore(haceSieteDias)
    }.sortedByDescending { it.fecha }

    Column(modifier = modifier) {
        NotificationsHeader()

        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            //Notificaciones de hoy
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
                items(notificacionesHoy) { notificacion ->
                    NotificationItem(notificacion)
                }
            }

            //Notificaciones esta semana
            if (notificacionEstaSemana.isNotEmpty()) {
                item {
                    Text(
                        text = "Esta semana",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 4.dp
                        )
                    )
                }
                items(notificacionEstaSemana) { notificacion ->
                    NotificationItem(notificacion)
                }
            }

            //Notificaciones anteriores
            if (notificacionesAnteriores.isNotEmpty()) {
                item {
                    Text(
                        text = "Anteriores",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 4.dp
                        )
                    )
                }
                items(notificacionesAnteriores) { notificacion ->
                    NotificationItem(notificacion)
                }
            }
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun NotificationsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NotificationsScreenContent()
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NotificationsScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        NotificationsScreen()
    }
}
