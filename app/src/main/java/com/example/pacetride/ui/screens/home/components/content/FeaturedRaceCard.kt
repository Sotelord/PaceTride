package com.example.pacetride.ui.screens.home.components.content

import android.util.Log
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
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.utils.AppButton

// ---------- TARJETA GRANDE DE CARRERA DESTACADA ----------

@Composable
fun FeaturedRaceCard(
    carrera: Carrera,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(carrera.idImagen?: R.drawable.running),
            contentDescription = carrera.nombre,
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
                            Color.Black.copy(alpha = 0.9f)
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
                carrera.nombre,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoRow(R.drawable.ic_calendario, carrera.fecha)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.ubicacion_blanco, carrera.ubicacion)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.corriendo, carrera.distanciaMostrable)
            Spacer(modifier = Modifier.height(8.dp))
            carrera.precioMostrable?.let {
                Text(
                    it,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            AppButton(
                textoBoton = stringResource(R.string.ver_carrera),
                onClick = { Log.d("HomeScreen", "Ver carrera clicked")},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun FeaturedRaceCardPreview(modifier: Modifier = Modifier){
    val carrera = LocalCarreraProvider.listCarrera[2]
    FeaturedRaceCard(
        carrera,
        modifier = Modifier.padding(horizontal = 20.dp)
        )
}