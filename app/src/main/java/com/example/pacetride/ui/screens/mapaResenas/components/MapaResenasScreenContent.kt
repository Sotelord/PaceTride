package com.example.pacetride.ui.screens.mapaResenas.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.theme.PacetrideTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaResenasScreenContent(
    resenas: List<Resena>,
    resenaSeleccionadaId: String?,
    isLoading: Boolean,
    errorMessage: String?,
    onResenaSeleccionada: (String) -> Unit,
    onMapaClick: () -> Unit,
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val peekHeight = 120.dp

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(4.7110, -74.0721), 11f)
    }

    val resenasConUbicacion = remember(resenas) {
        resenas.mapNotNull { r ->
            if (r.latitud != null && r.longitud != null) r to LatLng(
                r.latitud,
                r.longitud
            ) else null
        }
    }
    val resenaSeleccionada = remember(resenas, resenaSeleccionadaId) {
        resenas.find { it.id == resenaSeleccionadaId }
    }

    LaunchedEffect(resenaSeleccionada) {
        resenasConUbicacion
            .find { it.first.id == resenaSeleccionadaId }
            ?.let { (_, posicion) ->
                cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(posicion, 15f))
            }
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = peekHeight,
        sheetContent = {
            ResenasBottomSheetContent(
                resenas = resenas,
                resenaSeleccionadaId = resenaSeleccionadaId,
                isLoading = isLoading,
                errorMessage = errorMessage,
                onResenaClick = { id ->
                    onResenaSeleccionada(id)
                    scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                }
            )
        }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { onMapaClick() }
            ) {
                resenasConUbicacion.forEach { (resena, posicion) ->
                    val seleccionada = resena.id == resenaSeleccionadaId
                    Marker(
                        state = rememberMarkerState(key = resena.id, position = posicion),
                        icon = BitmapDescriptorFactory.defaultMarker(
                            if (seleccionada) BitmapDescriptorFactory.HUE_ORANGE
                            else 145f
                        ),
                        zIndex = if (seleccionada) 1f else 0f,
                        onClick = {
                            onResenaSeleccionada(resena.id)
                            true
                        }
                    )
                }
            }

            MapaHeader(
                atrasPressed = atrasPressed,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            resenaSeleccionada?.let { resena ->
                ResenaInfoCard(
                    resena = resena,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp, bottom = peekHeight + 16.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun MapaResenasScreenContentPreview() {
    PacetrideTheme(darkTheme = true) {
        MapaResenasScreenContent(
            resenas = LocalResenaProvider.listaResenas,
            resenaSeleccionadaId = null,
            isLoading = false,
            errorMessage = null,
            onResenaSeleccionada = {},
            onMapaClick = {},
            atrasPressed = {}
        )
    }
}

@Composable
@Preview
fun MapaResenasScreenContentPreview2() {
    PacetrideTheme(darkTheme = false) {
        MapaResenasScreenContent(
            resenas = LocalResenaProvider.listaResenas,
            resenaSeleccionadaId = null,
            isLoading = false,
            errorMessage = null,
            onResenaSeleccionada = {},
            onMapaClick = {},
            atrasPressed = {}
        )
    }
}