package com.example.pacetride.ui.screens.explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.screens.explorer.components.ExploreScreenContent
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    exploreViewModel: ExploreViewModel,
    verCarreraButtonPressed: (Int) -> Unit,
    comunidadPressed: () -> Unit,
    filtroDistanciaInicial: Int? = null
) {
    val state by exploreViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        exploreViewModel.inicializarConFiltro(filtroDistanciaInicial)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ExploreScreenContent(
            verCarreraButtonPressed = verCarreraButtonPressed,
            carreras = state.carreras,
            comunidadPressed = comunidadPressed,
            textoBusqueda = state.textoBusqueda,
            onTextoBusquedaChange = { exploreViewModel.updateTextoBusqueda(it) },
            filtroSeleccionado = state.filtroSeleccionado,
            onFiltroSeleccionadoChange = { exploreViewModel.updateFiltroSeleccionado(it) },
            filtrosVisibles = state.filtrosVisibles,
            onToggleFiltros = { exploreViewModel.toggleFiltrosVisibles() },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ExploreScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ExploreScreen(
            exploreViewModel = viewModel(),
            verCarreraButtonPressed = {},
            comunidadPressed = {}
        )
    }
}