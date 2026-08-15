package com.example.pacetride.ui.screens.home.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.AppButton

// ---------- TARJETA GRANDE DE CARRERA DESTACADA ----------

@Composable
fun FeaturedRaceCard(
    idImagen: Int,
    titulo: String,
    fecha: String,
    ubicacion: String,
    distancia: String,
    precio: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.85f)
                        ),
                        startY = 0f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoRow(R.drawable.ic_calendario, fecha)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.ubicacion_blanco, ubicacion)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.corriendo, distancia)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                precio,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppButton(
                textoBoton = stringResource(R.string.ver_carrera),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun FeaturedRaceCardPreview(modifier: Modifier = Modifier){
    FeaturedRaceCard(
        R.drawable.running,
        "Media Maratón de Bogotá 2026",
        "27 de septiembre de 2026",
        "Bogotá, Colombia",
        "21K",
        "$145.000 COP",
        modifier = Modifier.padding(horizontal = 20.dp)
        )
}