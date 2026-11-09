package com.example.pacetride.ui.screens.escribirResena

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
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
import com.example.pacetride.ui.utils.rememberVerificadorUbicacion


private const val MSG_SIN_PERMISO = "Sin permiso de ubicación, tu reseña no aparecerá en el mapa"
private const val MSG_GPS_APAGADO = "Para compartir tu ubicación debes tener la ubicación del dispositivo activada"
@Composable
fun EscribirResenaScreen(
    modifier: Modifier = Modifier,
    escribirResenaViewModel: EscribirResenaViewModel,
    raceId: String,
    reviewId: String? = null,
    atrasPressed: () -> Unit,
) {
    val state by escribirResenaViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val verificarUbicacion = rememberVerificadorUbicacion()

    val activarCompartirUbicacion = {
        verificarUbicacion { activa ->
            escribirResenaViewModel.setCompartirUbicacion(
                activo =activa,
                error = if(activa) null else MSG_GPS_APAGADO
            )
        }
    }

    val permisoLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        if (permisos.values.any { it }) {
            activarCompartirUbicacion()
        } else {
            escribirResenaViewModel.setCompartirUbicacion(false, MSG_SIN_PERMISO)
        }
    }

    val onToggleUbicacion: (Boolean) -> Unit = { activar ->
        if (!activar) {
            escribirResenaViewModel.setCompartirUbicacion(false)
        } else {
            val permisos = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            val tienePermiso = permisos.any {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
            if (tienePermiso) activarCompartirUbicacion() else permisoLauncher.launch(permisos)
        }
    }

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
        state.errorMessage != null && !state.mostrarMensajeError -> {
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
                    onPublicarClick = {
                        if (reviewId == null && state.compartirUbicacion) {
                            verificarUbicacion { activa ->
                                if (activa) {
                                    escribirResenaViewModel.createResena(reviewId)
                                } else {
                                    escribirResenaViewModel.setCompartirUbicacion(false, MSG_GPS_APAGADO)
                                }
                            }
                        } else {
                            escribirResenaViewModel.createResena(reviewId)
                        }
                    },
                    mostrarSelectorUbicacion = reviewId == null,
                    compartirUbicacion = state.compartirUbicacion,
                    errorUbicacion = state.errorUbicacion,
                    onToggleUbicacion = onToggleUbicacion,
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