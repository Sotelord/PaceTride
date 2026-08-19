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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.ProfileUiState
import com.example.pacetride.data.local.LocalCarreraHistorialProvider
import com.example.pacetride.data.local.LocalProfileUiStateProvider
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
    uiState: ProfileUiState,
    onEditarPerfilClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val historial = LocalCarreraHistorialProvider.historial

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        HeaderPerfil()

        Spacer(modifier = Modifier.height(24.dp))

        FotoPerfil(uiState.fotoPerfil)

        Spacer(modifier = Modifier.height(12.dp))

        DatosUsuario(
            nombre = uiState.nombre,
            usuario = uiState.usuario,
            ubicacion = uiState.ubicacion,
            bio = uiState.bio
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

        EstadisticasRow()

        Spacer(modifier = Modifier.height(32.dp))

        SeccionTitulo(
            stringResource(R.string.proximas_carreras),
            modifier = Modifier.fillMaxWidth()
        )

        NextRaceCardWithGraph(
            titulo = uiState.proximaCarreraTitulo,
            fecha = uiState.proximaCarreraFecha,
            distancia = uiState.proximaCarreraDistancia
        )

        Spacer(modifier = Modifier.height(32.dp))

        SeccionTitulo(
            stringResource(R.string.historial_de_carreras),
            modifier = Modifier.fillMaxWidth()
        )

        HistorialRow(historial)

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val usuario = LocalProfileUiStateProvider.usuario

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ProfileScreenContent(
            uiState = usuario,
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
    ProfileScreen()
}