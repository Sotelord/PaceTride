package com.example.pacetride.ui.screens.publicprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.profile.components.content.FotoPerfil
import com.example.pacetride.ui.screens.publicprofile.components.content.DatosUsuarioPublico
import com.example.pacetride.ui.screens.publicprofile.components.content.MisCarrerasRow
import com.example.pacetride.ui.screens.publicprofile.components.header.HeaderPublico
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.profile.components.estadisticas.EstadisticasRow
import com.example.pacetride.ui.screens.publicprofile.components.content.ResenasList
import com.example.pacetride.ui.screens.publicprofile.components.seccion.SeccionTitulo

@Composable
fun PublicProfileContent(
    usuario: Usuario,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderPublico()

        Spacer(modifier = Modifier.height(8.dp))

        FotoPerfil(usuario.fotoPerfil)

        Spacer(modifier = Modifier.height(12.dp))

        DatosUsuarioPublico(usuario)

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

        ResenasList(usuario.resenas)
    }
}
@Composable
fun PublicProfileScreen(modifier: Modifier = Modifier) {
    val usuario = LocalUsuarioProvider.usuarios[1]
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PublicProfileContent(
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
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PublicProfileScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        PublicProfileScreen()
    }
}