package com.example.pacetride.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.home.components.content.FeaturedRaceCard
import com.example.pacetride.ui.utils.SeccionTitulo
import com.example.pacetride.ui.screens.home.components.content.DistanciasRow
import com.example.pacetride.ui.screens.home.components.content.ProximasCarrerasRow
import com.example.pacetride.ui.screens.home.components.header.HeaderHomeScreen
import com.example.pacetride.ui.utils.LogoApp
import com.example.pacetride.ui.utils.navbar.BottomNavBar
import com.example.pacetride.ui.utils.navbar.Seccion

// ---------- CONTENIDO ----------

@Composable
fun HomeScreenContent(
    usuario: Usuario,
    featuredRace: Carrera,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderHomeScreen(usuario)
        Spacer(modifier = Modifier.height(20.dp))
        FeaturedRaceCard(
            featuredRace,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        SeccionTitulo(stringResource(R.string.pr_ximas_carreras))
        ProximasCarrerasRow(usuario.proximaCarreras)
        SeccionTitulo(stringResource(R.string.elige_tu_distancia))
        DistanciasRow()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val usuario = LocalUsuarioProvider.usuarios[2]
    val featuredRace = LocalCarreraProvider.listCarrera[2]
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeScreenContent(
            usuario,
            featuredRace,
            modifier = Modifier.weight(1f)
        )
        BottomNavBar(Seccion.INICIO)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun HomeScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        HomeScreen()
    }
}