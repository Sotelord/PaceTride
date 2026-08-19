package com.example.pacetride.ui.screens.publicprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.profile.components.content.FotoPerfil
import com.example.pacetride.ui.screens.publicprofile.components.content.DatosUsuarioPublico
import com.example.pacetride.ui.screens.publicprofile.components.content.EstadisticasRow
import com.example.pacetride.ui.screens.publicprofile.components.content.MisCarrerasRow
import com.example.pacetride.ui.screens.publicprofile.components.content.ResenaCard
import com.example.pacetride.ui.screens.publicprofile.components.header.HeaderPublico
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.screens.publicprofile.components.seccion.SeccionTitulo

@Composable
fun PublicProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderPublico()
            Spacer(modifier = Modifier.height(8.dp))

            FotoPerfil(R.drawable.foto_perfil)
            Spacer(modifier = Modifier.height(12.dp))

            DatosUsuarioPublico(
                nombre = "Laura Gómez",
                usuario = "@lauragomez",
                ubicacion = "Bogotá, Colombia",
                bio = "Runner apasionada por las carreras de ruta.\nSiempre buscando nuevos retos."
            )

            Spacer(modifier = Modifier.height(24.dp))

            SeccionTitulo(titulo = "Estadísticas", modifier = Modifier.fillMaxWidth())
            EstadisticasRow()

            Spacer(modifier = Modifier.height(24.dp))

            SeccionTitulo(titulo = "Mis carreras", modifier = Modifier.fillMaxWidth())
            MisCarrerasRow()

            Spacer(modifier = Modifier.height(24.dp))

            SeccionTitulo(titulo = "Últimas reseñas", modifier = Modifier.fillMaxWidth())
            ResenaCard()
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PublicProfileScreenPreview() {
    PublicProfileScreen()
}