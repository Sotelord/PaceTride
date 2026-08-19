package com.example.pacetride.ui.screens.comunidad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.local.LocalPublicacionProvider
import com.example.pacetride.ui.screens.comunidad.components.content.PostCard
import com.example.pacetride.ui.screens.comunidad.components.header.HeaderComunidad
import com.example.pacetride.ui.utils.navbar.BottomNavBar
import com.example.pacetride.ui.utils.navbar.Seccion

// ---------- CONTENIDO ----------

@Composable
fun ComunidadScreenContent(modifier: Modifier = Modifier) {
    val publicaciones = LocalPublicacionProvider.publicaciones

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderComunidad(modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(publicaciones) { publicacion ->
                PostCard(
                    idAvatar = publicacion.idAvatar,
                    nombre = publicacion.nombre,
                    tiempo = publicacion.tiempo,
                    texto = publicacion.texto,
                    distancia = publicacion.distancia,
                    tiempoCarrera = publicacion.tiempoCarrera,
                    ritmo = publicacion.ritmo,
                    calificacion = publicacion.calificacion,
                    likes = publicacion.likes,
                    comentarios = publicacion.comentarios
                )
            }
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ComunidadScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ComunidadScreenContent(
            modifier = Modifier.weight(1f)
        )
        BottomNavBar(Seccion.EXPLORAR)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ComunidadScreenPreview() {
    ComunidadScreen()
}