package com.example.pacetride.ui.screens.notifications.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.pacetride.ui.screens.notifications.components.item.Corazon

@Composable
fun NotificationItem(
    userName: String,
    action: String,
    subtext: String? = null,
    buttonText: String? = null,
    hasHeart: Boolean = false,
    hasGreenIndicator: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2A2A2A), RoundedCornerShape(12.dp))
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
                    .background(Color(0xFF4CAF50))
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                            append("$userName ")
                        }
                        withStyle(style = SpanStyle(color = Color.LightGray)) {
                            append(action)
                        }
                    },
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
                if (subtext != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = subtext, color = Color.LightGray, fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botón blanco del diseño
            if (buttonText != null) {
                Button(
                    onClick = { Log.d("NotificationsScreen", "Boton clicked")},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(text = buttonText, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else if (hasHeart) {
                Corazon(onClick = {Log.d("NotificationsScreen", "Corazon clicked")})
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun NotificationItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NotificationItem(
            userName = "Laura Gómez",
            action = "comenzó a seguirte.",
            buttonText = "Ver perfil",
            hasGreenIndicator = true
        )
        NotificationItem(
            userName = "Carlos Ramírez",
            action = "comentó tu reseña.",
            hasHeart = true
        )
        NotificationItem(
            userName = "Media Maratón",
            action = "se acerca pronto.",
            buttonText = "Ver carrera",
            hasGreenIndicator = true
        )
    }
}