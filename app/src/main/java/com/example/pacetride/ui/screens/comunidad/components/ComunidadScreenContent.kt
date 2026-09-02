package com.example.pacetride.ui.screens.comunidad.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.Publicacion
import com.example.pacetride.data.Usuario
import com.example.pacetride.ui.screens.comunidad.components.content.PostCard
import com.example.pacetride.ui.screens.comunidad.components.header.HeaderComunidad
import com.example.pacetride.ui.utils.AppButton

private fun filtrarPublicaciones(
    publicaciones: List<Publicacion>,
    textoBusqueda: String
): List<Publicacion> {
    if (textoBusqueda.isBlank()) return publicaciones

    return publicaciones.filter { publicacion ->
        publicacion.nombre.contains(textoBusqueda, ignoreCase = true) ||
                publicacion.textoAMostrar.contains(textoBusqueda, ignoreCase = true)
    }
}

@Composable
fun ComunidadScreenContent(
    publicaciones: List<Publicacion>,
    usuario: Usuario,
    escribirResenaPressed: () -> Unit,
    notificacionButtonPressed: (Int)-> Unit,
    textoBusqueda: String,
    onTextoBusquedaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val publicacionesFiltradas = remember(publicaciones, textoBusqueda) {
        filtrarPublicaciones(publicaciones, textoBusqueda)
    }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderComunidad(
            textoBusqueda = textoBusqueda,
            usuario = usuario,
            onTextoBusquedaChange = onTextoBusquedaChange,
            notificacionButtonPressed = notificacionButtonPressed,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(publicacionesFiltradas) { publicacion ->
                PostCard(
                    idAvatar = publicacion.idAvatar,
                    nombre = publicacion.nombre,
                    tiempo = publicacion.tiempo,
                    texto = publicacion.textoAMostrar,
                    estadisticasCarrera = publicacion.estadisiticas,
                    resena = publicacion.resena,
                    likes = publicacion.likes,
                    comentarios = publicacion.comentarios
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            AppButton(
                textoBoton = stringResource(R.string.escribir_resena),
                onClick = {
                    Log.d("ExplorerScreen", "Escribir reseña clicked")
                    escribirResenaPressed()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}