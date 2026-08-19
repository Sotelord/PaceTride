package com.example.pacetride.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.home.components.content.FeaturedRaceCard
import com.example.pacetride.ui.utils.SeccionTitulo
import com.example.pacetride.ui.screens.home.components.content.DistanciasRow
import com.example.pacetride.ui.screens.home.components.content.ProximasCarrerasRow
import com.example.pacetride.ui.screens.home.components.header.HeaderHomeScreen
import com.example.pacetride.ui.utils.navbar.BottomNavBar
import com.example.pacetride.ui.utils.navbar.Seccion

// ---------- CONTENIDO ----------

@Composable
fun HomeScreenContent(
    idImagen: Int,
    titulo: String,
    fecha: String,
    ubicacion: String,
    distancia: String,
    precio: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderHomeScreen()
        Spacer(modifier = Modifier.height(20.dp))
        FeaturedRaceCard(
            idImagen = idImagen,
            titulo = titulo,
            fecha = fecha,
            ubicacion = ubicacion,
            distancia = distancia,
            precio = precio,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        SeccionTitulo("Próximas carreras")
        ProximasCarrerasRow()
        SeccionTitulo("Elige tu distancia")
        DistanciasRow()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var idImagen by remember { mutableStateOf(R.drawable.running) }
    var titulo by remember { mutableStateOf("Media Maratón de Bogotá 2026") }
    var fecha by remember { mutableStateOf("27 de septiembre de 2026") }
    var ubicacion by remember { mutableStateOf("Bogotá, Colombia") }
    var distancia by remember { mutableStateOf("21K") }
    var precio by remember { mutableStateOf("$145.000 COP") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HomeScreenContent(
            idImagen,
            titulo,
            fecha,
            ubicacion,
            distancia,
            precio,
            modifier = Modifier.weight(1f)
        )
        BottomNavBar(Seccion.INICIO)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun HomeScreenPreview() {
    HomeScreen()
}