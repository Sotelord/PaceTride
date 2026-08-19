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
import com.example.pacetride.data.Notificacion
import androidx.compose.foundation.lazy.items
import com.example.pacetride.data.local.LocalNotificacionProvider

@Composable
fun NotificationsScreen(
    notificacionesHoy: List<Notificacion>,
    notificacionesEstaSemana: List<Notificacion>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NotificationsHeader()

        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "Hoy",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            // Notificaciones de hoy
            items(notificacionesHoy) { notificacion ->
                NotificationItem(
                    userName = notificacion.userName,
                    action = notificacion.action,
                    subtext = notificacion.subtext,
                    buttonText = notificacion.buttonText,
                    hasHeart = notificacion.hasHeart ?: false
                )
            }

            item {
                Text(
                    text = "Esta semana",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 4.dp
                    )
                )
            }

            // Notificaciones de esta semana
            items(notificacionesEstaSemana) { notificacion ->
                NotificationItem(
                    userName = notificacion.userName,
                    action = notificacion.action,
                    subtext = notificacion.subtext,
                    buttonText = notificacion.buttonText,
                    hasHeart = notificacion.hasHeart ?: false
                )
            }
        }
    }
}


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NotificationsScreenPreview() {
    val notificacionesHoy = LocalNotificacionProvider.notificacionHoy
    val notificacionEstaSemana = LocalNotificacionProvider.notificacionEstaSemana
    NotificationsScreen(notificacionesHoy, notificacionEstaSemana)
}
