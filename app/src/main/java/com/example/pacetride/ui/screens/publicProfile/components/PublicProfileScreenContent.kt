package com.example.pacetride.ui.screens.publicProfile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.publicProfile.components.content.DatosUsuarioPublico
import com.example.pacetride.ui.screens.publicProfile.components.content.MisCarrerasRow
import com.example.pacetride.ui.screens.publicProfile.components.content.ResenasList
import com.example.pacetride.ui.screens.publicProfile.components.header.HeaderPublico
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.ProfileAsyncImage
import com.example.pacetride.ui.utils.TituloSeccionDetalle
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

        ProfileAsyncImage(imageURL = usuario.fotoPerfil, size = 100, imgSize = 48)

        Spacer(modifier = Modifier.height(12.dp))

        DatosUsuarioPublico(usuario, configPressed)

        Spacer(modifier = Modifier.height(24.dp))

        TituloSeccionDetalle(
            texto = stringResource(R.string.estad_sticas),
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
        )

        EstadisticasRow(usuario.estadisticasGlobales)

        Spacer(modifier = Modifier.height(24.dp))

        TituloSeccionDetalle(
            texto = stringResource(R.string.mis_carreras),
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
        )

        MisCarrerasRow(usuario.historial)

        Spacer(modifier = Modifier.height(24.dp))

        TituloSeccionDetalle(
            texto = stringResource(R.string.ultimas_resenas),
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
        )

        ResenasList(usuario.resenas, comentariosPressed)
    }
}

@Composable
@Preview
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