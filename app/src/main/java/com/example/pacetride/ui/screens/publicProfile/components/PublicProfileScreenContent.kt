package com.example.pacetride.ui.screens.publicProfile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.profile.components.content.FotoPerfil
import com.example.pacetride.ui.screens.publicProfile.components.content.DatosUsuarioPublico
import com.example.pacetride.ui.screens.publicProfile.components.content.MisCarrerasRow
import com.example.pacetride.ui.screens.publicProfile.components.content.ResenasList
import com.example.pacetride.ui.screens.publicProfile.components.header.HeaderPublico
import com.example.pacetride.ui.screens.publicProfile.components.seccion.SeccionTitulo
import com.example.pacetride.ui.theme.PacetrideTheme
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
@Preview(showBackground = true)
fun PublicProfileScreenContentPreview(){
    val usuario = LocalUsuarioProvider.usuarios[1]
    PacetrideTheme(darkTheme = true) {
        PublicProfileContent(
            atrasPressed = {},
            configPressed = {},
            comentariosPressed = {},
            usuario = usuario
        )
    }
}