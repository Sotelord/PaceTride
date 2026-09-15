package com.example.pacetride.ui.screens.notifications.components.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.data.Notificacion
import com.example.pacetride.data.local.LocalNotificacionProvider
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.ProfileAsyncImage

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notificacion: Notificacion,
    fotoOverride: String? = null,
    viewProfile: (Int) -> Unit,
    verCarrera: (Int) -> Unit = {},
    onClick: () -> Unit = {}
) {
    val nombre = notificacion.usuario?.nombre ?: notificacion.carrera?.nombre ?: ""
    val imagenUrl = fotoOverride ?: notificacion.usuario?.fotoPerfil ?: notificacion.carrera?.raceImageUrl
    val iconoError = if (notificacion.carrera != null) R.drawable.ic_race else R.drawable.ic_user_profile

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador verde: visible SOLO si no está leída
        if (!notificacion.leida) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileAsyncImage(
                imageURL = imagenUrl,
                size = 48,
                imgSize = 22,
                background = MaterialTheme.colorScheme.background,
                errorIcon = iconoError
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)) {
                            append("$nombre ")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                            append(notificacion.action)
                        }
                    },
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
                if (notificacion.subtext != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = notificacion.subtext, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (notificacion.buttonText != null) {
                Button(
                    onClick = {
                        // Una notificación es de carrera o de usuario, nunca ambas a la vez;
                        // priorizamos carrera porque si viene seteada, es lo que el botón debe abrir.
                        val carreraId = notificacion.carrera?.id
                        if (carreraId != null) {
                            verCarrera(carreraId)
                            Log.d("NotificationsScreen", "Botón clicked -> carrera $carreraId")
                        } else {
                            viewProfile(notificacion.usuario?.id ?: 0)
                            Log.d("NotificationsScreen", "Botón clicked -> perfil")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(text = notificacion.buttonText, color = MaterialTheme.colorScheme.background, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else if (notificacion.hasHeart) {
                Corazon(onClick = { Log.d("NotificationsScreen", "Corazon clicked") })
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun NotificationItemPreview() {
    val notificaciones = LocalNotificacionProvider.notificaciones
    PacetrideTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NotificationItem(notificacion = notificaciones[0], viewProfile = {})
            NotificationItem(notificacion = notificaciones[1], viewProfile = {})
            NotificationItem(notificacion = notificaciones[2], viewProfile = {})
            NotificationItem(notificacion = notificaciones[3], viewProfile = {})
            NotificationItem(notificacion = notificaciones[4], viewProfile = {})
        }
    }
}