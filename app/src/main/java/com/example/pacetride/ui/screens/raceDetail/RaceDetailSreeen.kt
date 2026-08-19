package com.example.pacetride.ui.screens.raceDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.raceDetail.components.content.BotonInscripcion
import com.example.pacetride.ui.screens.raceDetail.components.content.DistanciasDetalleRow
import com.example.pacetride.ui.screens.raceDetail.components.content.ImagenPortadaCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.IncluyeGrid
import com.example.pacetride.ui.screens.raceDetail.components.content.MapaRuta
import com.example.pacetride.ui.screens.raceDetail.components.content.TarjetaInfoCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.TituloCarrera
import com.example.pacetride.ui.utils.TituloSeccionDetalle

// Datos que cambian según la distancia elegida
data class InfoDistancia(
    val distancia: String,
    val precio: String
)

private val infoPorDistancia = mapOf(
    "5K" to InfoDistancia(distancia = "5 kilómetros", precio = "$60.000 COP"),
    "10K" to InfoDistancia(distancia = "10 kilómetros", precio = "$95.000 COP"),
    "21K" to InfoDistancia(distancia = "21 kilómetros", precio = "$145.000 COP")
)

// ---------- CONTENIDO ----------

@Composable
fun RaceDetailScreenContent(modifier: Modifier = Modifier) {
    var distanciaSeleccionada by remember { mutableStateOf("21K") }
    val infoActual = infoPorDistancia[distanciaSeleccionada] ?: infoPorDistancia.getValue("21K")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        ImagenPortadaCarrera(R.drawable.running)

        Column(modifier = Modifier.padding(16.dp)) {
            TituloCarrera(
                titulo = "Media Maratón Bogotá 2026",
                fecha = "27 de septiembre de 2026",
                ubicacion = "Bogotá, Colombia"
            )
            Spacer(modifier = Modifier.height(24.dp))

            DistanciasDetalleRow(
                distancias = listOf("5K", "10K", "21K"),
                seleccionada = distanciaSeleccionada,
                onSeleccionar = { distancia -> distanciaSeleccionada = distancia }
            )
            Spacer(modifier = Modifier.height(24.dp))

            TarjetaInfoCarrera(
                fecha = "27 de septiembre de 2026",
                hora = "6:00 a. m.",
                lugar = "Bogotá, Colombia",
                distancia = infoActual.distancia,
                precio = infoActual.precio
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                stringResource(R.string.ltimos_cupos_disponibles),
                color = colorResource(R.color.pulse_orange),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.sobre_la_carrera))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                stringResource(R.string.descripcion_carrera),
                color = colorResource(R.color.ice),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.ruta))
            Spacer(modifier = Modifier.height(8.dp))
            MapaRuta(R.drawable.mapa_ruta)
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.tu_inscripcion_incluye))
            Spacer(modifier = Modifier.height(16.dp))
            IncluyeGrid()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun RaceDetailScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.midnight))
                    .padding(16.dp)
            ) {
                BotonInscripcion(precio = "$145.000 COP")
            }
        }
    ) { innerPadding ->
        RaceDetailScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun RaceDetailScreenPreview() {
    RaceDetailScreen()
}