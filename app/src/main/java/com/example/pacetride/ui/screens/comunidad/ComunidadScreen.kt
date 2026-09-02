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
    escribirResenaPressed: () -> Unit,
    notificacionButtonPressed: (Int)-> Unit,
    modifier: Modifier = Modifier
) {
    val state by comunidadViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ComunidadScreenContent(
            publicaciones = state.publicaciones,
            usuario = state.usuario,
            escribirResenaPressed = escribirResenaPressed,
            notificacionButtonPressed = notificacionButtonPressed,
            textoBusqueda = state.textoBusqueda,
            onTextoBusquedaChange = {comunidadViewModel.updateTextoBusqueda(it)},
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
            escribirResenaPressed = {},
            notificacionButtonPressed = {}
        )
    }
}