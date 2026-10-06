package com.example.pacetride.ui.screens.escribirResena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.escribirResena.components.EscribirResenaScreenContent

@Composable
fun EscribirResenaScreen(
    modifier: Modifier = Modifier,
    escribirResenaViewModel: EscribirResenaViewModel,
    raceId: String,
    reviewId: String? = null,
    atrasPressed: () -> Unit,
) {
    val state by escribirResenaViewModel.uiState.collectAsState()
    val opcionesDestacar = remember {
        listOf("Ruta", "Organización", "Ambiente", "Hidratación", "Seguridad", "Kit", "Precio")
    }

    LaunchedEffect(Unit) {
        escribirResenaViewModel.getCarreraId(raceId)
        if(reviewId != null){
            escribirResenaViewModel.getResenaById(reviewId)
        }
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.errorMessage ?: "Error desconocido")
            }
        }

        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                EscribirResenaScreenContent(
                    carrera = state.carrera,
                    resenaId = reviewId,
                    calificacion = state.calificacion,
                    onCalificacionChange = { escribirResenaViewModel.updateCalificacion(it) },
                    textoResena = state.textoResena,
                    onTextoResenaChange = { escribirResenaViewModel.updateTextoResena(it) },
                    opcionesDestacar = opcionesDestacar,
                    seleccionadas = state.seleccionadas,
                    onToggleDestacar = { opcion -> escribirResenaViewModel.toggleDestacar(opcion)},
                    onPublicarClick = { escribirResenaViewModel.createResena("3", reviewId) },
                    mostrarMensajeError = state.mostrarMensajeError,
                    errorMessage = state.errorMessage,
                    atrasPressed = atrasPressed,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun EscribirResenaScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        EscribirResenaScreen(
            atrasPressed = {},
            raceId = "2",
            escribirResenaViewModel = viewModel()
        )
    }
}