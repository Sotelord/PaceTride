package com.example.pacetride.ui.screens.home.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- TARJETA PEQUEÑA DE CARRERA ----------
@Composable
fun RaceCard(
    idImagen: Int,
    titulo: String,
    precio: String,
    distancia: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(precio, color = Color.LightGray, fontSize = 10.sp)
                Text(
                    distancia,
                    color = Color.White,
                    fontSize = 8.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.DarkGray)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun RaceCardPreview(modifier: Modifier = Modifier){
    RaceCard(R.drawable.running, "Carrera 10K Bogotá", "$95.000 COP", "10K")
}

@Composable
@Preview
fun RaceCardPreview2(modifier: Modifier = Modifier){
    RaceCard(R.drawable.running, "Carrera 5K Universitaria", "$65.000 COP", "5K")
}

@Composable
@Preview
fun RaceCardPreview3(modifier: Modifier = Modifier){
    RaceCard(R.drawable.running, "Media Maratón Bogotá", "$145.000 COP", "21K")
}