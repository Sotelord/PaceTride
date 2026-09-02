package com.example.pacetride.ui.screens.escribirResena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.escribirResena.components.EscribirResenaScreenContent

@Composable
fun EscribirResenaScreen(
    escribirResenaViewModel: EscribirResenaViewModel,
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by escribirResenaViewModel.uiState.collectAsState()
    val opcionesDestacar = remember {
        listOf("Ruta", "Organización", "Ambiente", "Hidratación", "Seguridad", "Kit", "Precio")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        EscribirResenaScreenContent(
            carrera = state.carrera,
            calificacion = state.calificacion,
            onCalificacionChange = { escribirResenaViewModel.updateCalificacion(it) },
            textoResena = state.textoResena,
            onTextoResenaChange = { escribirResenaViewModel.updateTextoResena(it) },
            opcionesDestacar = opcionesDestacar,
            seleccionadas = state.seleccionadas,
            onToggleDestacar = { opcion -> escribirResenaViewModel.toggleDestacar(opcion)},
            onPublicarClick = { escribirResenaViewModel.publicarButtonPressed() },
            mostrarMensajeError = state.mostrarMensajeError,
            errorMessage = state.errorMessage,
            atrasPressed = atrasPressed,
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun EscribirResenaScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        EscribirResenaScreen(
            atrasPressed = {},
            escribirResenaViewModel = viewModel()
        )
    }
}