package com.example.pacetride.ui.screens.notifications.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.data.Notificacion
import com.example.pacetride.data.local.LocalNotificacionProvider
import com.example.pacetride.ui.screens.notifications.components.item.Corazon

@Composable
fun NotificationItem(
    notificacion: Notificacion,
    hasGreenIndicator: Boolean = true
) {
    val nombre = notificacion.usuario?.nombre?: notificacion.carrera?.nombre?: ""

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background((MaterialTheme.colorScheme.secondaryContainer),
                RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .padding(end = 12.dp), // Padding general menos en la izquierda para la barra verde
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador verde
        if (hasGreenIndicator) {
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
            Image(
                painter = painterResource(id = R.drawable.foto_perfil),
                contentDescription = "Foto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp).clip(CircleShape)
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

            // Botón blanco del diseño
            if (notificacion.buttonText != null) {
                Button(
                    onClick = { Log.d("NotificationsScreen", "Boton clicked")},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(text = notificacion.buttonText, color = MaterialTheme.colorScheme.background, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else if (notificacion.hasHeart == true) {
                Corazon(onClick = {Log.d("NotificationsScreen", "Corazon clicked")})
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun NotificationItemPreview() {
    val noticaciones = LocalNotificacionProvider.notificaciones
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NotificationItem(noticaciones[0])
        NotificationItem(noticaciones[1])
        NotificationItem(noticaciones[2])
    }
}