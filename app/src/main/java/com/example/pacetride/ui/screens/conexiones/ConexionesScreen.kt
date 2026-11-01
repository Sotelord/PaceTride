package com.example.pacetride.ui.screens.conexiones

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pacetride.ui.screens.conexiones.components.ConexionesScreenContent

@Composable
fun ConexionesScreen(
    conexionesViewModel: ConexionesViewModel,
    usuarioId: String,
    tipo: TipoConexion,
    atrasPressed: () -> Unit,
    onClickUsuario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by conexionesViewModel.uiSstate.collectAsState()

    LaunchedEffect(Unit) {
        conexionesViewModel.cargar(usuarioId, tipo)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            state.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            else -> ConexionesScreenContent(
                usuarios = state.usuarios,
                tipo = tipo,
                atrasPressed = atrasPressed,
                onClickUsuario = onClickUsuario
            )
        }
    }
}