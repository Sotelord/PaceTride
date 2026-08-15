package com.example.pacetride.ui.screens.explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.explorer.components.content.GrillaCarreras
import com.example.pacetride.ui.screens.explorer.components.header.HeaderExplorar
import com.example.pacetride.ui.utils.SeccionTitulo
import com.example.pacetride.ui.utils.navbar.BottomNavBar
import com.example.pacetride.ui.screens.explorer.components.searchBar.BusquedaYFiltros
import com.example.pacetride.ui.screens.explorer.components.searchBar.FiltrosRow
import com.example.pacetride.ui.screens.explorer.model.CarreraExplorar

// ---------- CONTENIDO ----------

@Composable
fun ExploreScreenContent(modifier: Modifier = Modifier) {
    val carreras = listOf(
        CarreraExplorar(R.drawable.running, "Carrera Atlética Bogotá 10K", "Bogotá", "15 de agosto", "10K", "$90.000 COP"),
        CarreraExplorar(R.drawable.running, "Corre por Bogotá 5K", "Bogotá", "30 de agosto", "5K", "$65.000 COP"),
        CarreraExplorar(R.drawable.running, "Media Maratón Bogotá 2026", "Bogotá", "27 de septiembre", "21K", "$145.000 COP"),
        CarreraExplorar(R.drawable.running, "Correra 0 Bogotá", "Bogotá", "27 de agosto", "", ultimosCupos = true)
    )

    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderExplorar()
        Spacer(modifier = Modifier.height(16.dp))
        BusquedaYFiltros()
        Spacer(modifier = Modifier.height(14.dp))
        FiltrosRow()
        SeccionTitulo(
            stringResource(R.string.carreras_cerca_de_ti),
            modifier = Modifier.padding(horizontal = 0.dp)
        )
        GrillaCarreras(carreras = carreras, modifier = Modifier.weight(1f))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ExploreScreenContent(
            modifier = Modifier.weight(1f)
        )
        BottomNavBar()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ExploreScreenPreview() {
    ExploreScreen()
}