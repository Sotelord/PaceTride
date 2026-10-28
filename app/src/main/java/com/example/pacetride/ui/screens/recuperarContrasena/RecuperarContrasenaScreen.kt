package com.example.pacetride.ui.screens.recuperarContrasena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.pacetride.ui.screens.recuperarContrasena.components.RecuperarContrasenaScreenContent

@Composable
fun RecuperarContrasenaScreen(
    viewModel: RecuperarContrasenaViewModel,
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        RecuperarContrasenaScreenContent(
            email = state.email,
            onEmailChange = { viewModel.updateEmail(it) },
            mensaje = state.mensaje,
            exito = state.exito,
            enviarPressed = { viewModel.enviarCorreo() },
            atrasPressed = atrasPressed,
            modifier = Modifier.weight(1f)
        )
    }
}