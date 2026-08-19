package com.example.pacetride.ui.screens.publicprofile.components.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun ResenaCard(modifier: Modifier = Modifier) {
    var meGustaActivo by remember { mutableStateOf(false) }
    var cantidadLikes by remember { mutableIntStateOf(34) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF2A2A2A), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = "⭐ 4,5/5", color = Color(0xFFFFD700), fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Media Maratón Bogotá 2026", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Muy buena organización, excelente ambiente y una ruta exigente pero increíble.",
            color = Color.LightGray,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // ---------- LIKE ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        Log.d("PubliProfileScreen", "Like clicked")
                        meGustaActivo = !meGustaActivo
                        cantidadLikes += if (meGustaActivo) 1 else -1
                    }
                )
            ) {
                Image(
                    painter = painterResource(
                        id = if (meGustaActivo) R.drawable.ic_me_gusta_lleno else R.drawable.ic_me_gusta
                    ),
                    contentDescription = "Likes",
                    colorFilter = if (meGustaActivo) ColorFilter.tint(colorResource(R.color.electric_lime)) else null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = cantidadLikes.toString(),
                    color = if (meGustaActivo) colorResource(R.color.electric_lime) else Color.LightGray,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // ---------- COMENTARIOS ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { Log.d("PubliProfileScreen", "Comentario clicked") }
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_comentario),
                    contentDescription = "Comentarios",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "6", color = Color.LightGray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
@Preview
fun ResenaCardPreview(modifier: Modifier = Modifier){
    ResenaCard()
}