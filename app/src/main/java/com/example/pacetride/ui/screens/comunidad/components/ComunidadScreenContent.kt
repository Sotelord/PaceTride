package com.example.pacetride.ui.screens.comunidad.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.comunidad.components.content.TabsComunidad
import com.example.pacetride.ui.screens.comunidad.components.header.HeaderComunidad
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.reviews.ResenasList

private fun filtrarResenas(resenas: List<Resena>, texto: String): List<Resena> {
    if (texto.isBlank()) return resenas
    return resenas.filter { r ->
        r.usuario?.contains(texto, ignoreCase = true) == true ||
                r.userName?.contains(texto, ignoreCase = true) == true ||
                r.carrera?.contains(texto, ignoreCase = true) == true ||
                r.resena.contains(texto, ignoreCase = true)
    }
}

@Composable
fun ComunidadScreenContent(
    resenas: List<Resena>,
    usuario: Usuario,
    sesion: String,
    tabSeleccionada: Int,
    onTabSelected: (Int) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    textoBusqueda: String,
    onTextoBusquedaChange: (String) -> Unit,
    sendOrDeleteResenaLike: (String, String) -> Unit,
    notificacionButtonPressed: (String) -> Unit,
    onClickUsuario: (String) -> Unit,
    onClickCarrera: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resenasFiltradas = remember(resenas, textoBusqueda) {
        filtrarResenas(resenas, textoBusqueda)
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
        Spacer(modifier = Modifier.height(8.dp))
        TabsComunidad(selectedTabIndex = tabSeleccionada, onTabSelected = onTabSelected)
        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            errorMessage != null -> Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { Text(text = errorMessage, color = MaterialTheme.colorScheme.onBackground) }

            else -> Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                ResenasList(
                    resenas = resenasFiltradas,
                    sesion = sesion,
                    sendOrDeleteResenaLike = sendOrDeleteResenaLike,
                    onClickUsuario = onClickUsuario,
                    onClickCarrera = onClickCarrera,
                    mensajeVacio = if (tabSeleccionada == 0)
                        "Aún no hay reseñas."
                    else
                        "Las personas que sigues aún no han publicado reseñas."
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
@Preview
fun ComunidadScreenContentPreview() {
    val resenas = LocalResenaProvider.listaResenas
    val usuario = LocalUsuarioProvider.usuarios[0]
    PacetrideTheme(darkTheme = true) {
        ComunidadScreenContent(
            resenas = resenas,
            usuario = usuario,
            sesion = "",
            tabSeleccionada = 0,
            onTabSelected = {},
            isLoading = false,
            errorMessage = null,
            textoBusqueda = "",
            onTextoBusquedaChange = {},
            sendOrDeleteResenaLike = { _, _ -> },
            notificacionButtonPressed = {},
            onClickUsuario = {},
            onClickCarrera = {}
        )
    }
}