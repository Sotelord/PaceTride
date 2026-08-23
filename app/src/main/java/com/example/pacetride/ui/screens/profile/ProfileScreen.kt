package com.example.pacetride.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.profile.components.content.DatosUsuario
import com.example.pacetride.ui.screens.profile.components.content.FotoPerfil
import com.example.pacetride.ui.screens.profile.components.estadisticas.NextRaceCardWithGraph
import com.example.pacetride.ui.screens.profile.components.estadisticas.EstadisticasRow
import com.example.pacetride.ui.screens.profile.components.header.HeaderPerfil
import com.example.pacetride.ui.screens.profile.components.history.HistorialRow
import com.example.pacetride.ui.utils.AppButton
import com.example.pacetride.ui.utils.SeccionTitulo
import com.example.pacetride.ui.utils.navbar.BottomNavBar
import com.example.pacetride.ui.utils.navbar.Seccion

// ---------- CONTENIDO ----------

@Composable
fun ProfileScreenContent(
    usuario: Usuario,
    onEditarPerfilClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        HeaderPerfil()

        Spacer(modifier = Modifier.height(24.dp))

        FotoPerfil(usuario.fotoPerfil)

        Spacer(modifier = Modifier.height(12.dp))

        DatosUsuario(
            nombre = usuario.nombre,
            usuario = usuario.usuario,
            ubicacion = usuario.ubicacion,
            bio = usuario.bio
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppButton(
            textoBoton = stringResource(R.string.editar_perfil),
            onClick = onEditarPerfilClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        SeccionTitulo(
            stringResource(R.string.mis_estadisticas),
            modifier = Modifier.fillMaxWidth()
        )

        EstadisticasRow(usuario.estadisticasGlobales)

        Spacer(modifier = Modifier.height(32.dp))

        SeccionTitulo(
            stringResource(R.string.proximas_carreras),
            modifier = Modifier.fillMaxWidth()
        )

        NextRaceCardWithGraph(
            usuario.proximaCarreras[2]
        )

        Spacer(modifier = Modifier.height(32.dp))

        SeccionTitulo(
            stringResource(R.string.historial_de_carreras),
            modifier = Modifier.fillMaxWidth()
        )

        HistorialRow(usuario.historial)

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val usuario = LocalUsuarioProvider.usuarios[2]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ProfileScreenContent(
            usuario = usuario,
            onEditarPerfilClick = {
                Log.d("ProfileScreen", "Editar perfil clicked")
            },
            modifier = Modifier.weight(1f)
        )

        BottomNavBar(Seccion.PERFIL)
    }
}


@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ProfileScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ProfileScreen()
    }
}