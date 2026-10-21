package com.example.pacetride.ui.screens.comunidad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.screens.comunidad.components.ComunidadScreenContent
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ComunidadScreen(
    comunidadViewModel: ComunidadViewModel,
    notificacionButtonPressed: (String) -> Unit,
    onClickUsuario: (String) -> Unit,
    onClickCarrera: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by comunidadViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ComunidadScreenContent(
            resenas = state.resenas,
            usuario = state.usuario,
            sesion = state.sesion,
            tabSeleccionada = state.tabSeleccionada,
            onTabSelected = { comunidadViewModel.selectTab(it) },
            isLoading = state.isLoading,
            errorMessage = state.errorMessage,
            textoBusqueda = state.textoBusqueda,
            onTextoBusquedaChange = { comunidadViewModel.updateTextoBusqueda(it) },
            sendOrDeleteResenaLike = { resenaId, userId ->
                comunidadViewModel.sendOrDeleteResenaLike(resenaId, userId)
            },
            notificacionButtonPressed = notificacionButtonPressed,
            onClickUsuario = onClickUsuario,
            onClickCarrera = onClickCarrera,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ComunidadScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ComunidadScreen(
            comunidadViewModel = viewModel(),
            notificacionButtonPressed = {},
            onClickUsuario = {},
            onClickCarrera = {}
        )
    }
}