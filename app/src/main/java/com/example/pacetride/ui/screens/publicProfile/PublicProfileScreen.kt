package com.example.pacetride.ui.screens.publicProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.profile.components.content.FotoPerfil
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.publicProfile.Components.content.DatosUsuarioPublico
import com.example.pacetride.ui.screens.publicProfile.Components.content.MisCarrerasRow
import com.example.pacetride.ui.screens.publicProfile.Components.content.ResenasList
import com.example.pacetride.ui.screens.publicProfile.Components.header.HeaderPublico
import com.example.pacetride.ui.screens.publicProfile.Components.seccion.SeccionTitulo
import com.example.pacetride.ui.utils.stats.EstadisticasRow

@Composable
fun PublicProfileContent(
    usuario: Usuario,
    atrasPressed: () -> Unit,
    comentariosPressed: () -> Unit,
    configPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderPublico(atrasPressed)

        Spacer(modifier = Modifier.height(8.dp))

        FotoPerfil(usuario.fotoPerfil)

        Spacer(modifier = Modifier.height(12.dp))

        DatosUsuarioPublico(usuario, configPressed)

        Spacer(modifier = Modifier.height(24.dp))

        SeccionTitulo(
            titulo = stringResource(R.string.estad_sticas),
            modifier = Modifier.fillMaxWidth()
        )

        EstadisticasRow(usuario.estadisticasGlobales)

        Spacer(modifier = Modifier.height(24.dp))

        SeccionTitulo(
            titulo = stringResource(R.string.mis_carreras),
            modifier = Modifier.fillMaxWidth()
        )

        MisCarrerasRow(usuario.historial)

        Spacer(modifier = Modifier.height(24.dp))

        SeccionTitulo(
            titulo = stringResource(R.string.ultimas_resenas),
            modifier = Modifier.fillMaxWidth()
        )

        ResenasList(usuario.resenas, comentariosPressed)
    }
}
@Composable
fun PublicProfileScreen(
    usuarioId: Int,
    atrasPressed: () -> Unit,
    comentariosPressed: () -> Unit,
    configPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val usuario = LocalUsuarioProvider.usuarios.find { it.id == usuarioId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if(usuario != null){
            PublicProfileContent(
                atrasPressed = atrasPressed,
                comentariosPressed = comentariosPressed,
                configPressed = configPressed,
                usuario = usuario,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 32.dp
                    )
            )
        } else {
            Text("Usuario no encontrados")
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PublicProfileScreenPreview() {
    val usuario = LocalUsuarioProvider.usuarios[1]
    PacetrideTheme(darkTheme = true) {
        PublicProfileScreen(2, {}, {}, {})
    }
}