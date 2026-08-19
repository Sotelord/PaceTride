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

@Composable
fun NotificationsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NotificationsHeader()

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text("Hoy", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp))
            }
            item {
                NotificationItem(
                    userName = "Laura Gómez",
                    action = "comenzó a seguirte.",
                    buttonText = "Ver perfil"
                )
            }
            item {
                NotificationItem(
                    userName = "Carlos Ramírez",
                    action = "comentó tu reseña de la Media Maratón Bogotá 2026.",
                    subtext = "¡Excelente tiempo! 🔥"
                )
            }
            item {
                NotificationItem(
                    userName = "Tu reseña",
                    action = "recibió 12 nuevos Me gusta.",
                    hasHeart = true
                )
            }
            item {
                NotificationItem(
                    userName = "María López",
                    action = "respondió a tu comentario.",
                    subtext = "También me encantó la ruta."
                )
            }

            item {
                Text("Esta semana", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
            }
            item {
                NotificationItem(
                    userName = "Media Maratón Bogotá 2026",
                    action = "se acerca.",
                    subtext = "Faltan 47 días.",
                    buttonText = "Ver carrera"
                )
            }
            item {
                NotificationItem(
                    userName = "Diego Rodríguez",
                    action = "empezó a seguirte.",
                    buttonText = "Ver perfil"
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen()
}
