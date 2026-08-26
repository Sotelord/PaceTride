package com.example.pacetride.ui.screens.explorer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.screens.explorer.components.content.GrillaCarreras
import com.example.pacetride.ui.screens.explorer.components.header.HeaderExplorar
import com.example.pacetride.ui.utils.SeccionTitulo
import com.example.pacetride.ui.screens.explorer.components.searchBar.BusquedaYFiltros
import com.example.pacetride.ui.screens.explorer.components.searchBar.FiltrosRow

// ---------- LÓGICA DE FILTRADO ----------

private fun filtrarCarreras(
    carreras: List<Carrera>,
    textoBusqueda: String,
    filtroSeleccionado: String
): List<Carrera> {
    val kmFiltro = filtroSeleccionado.removeSuffix("K").toIntOrNull()

    return carreras.filter { carrera ->
        val coincideBusqueda = textoBusqueda.isBlank() ||
                carrera.nombre.contains(textoBusqueda, ignoreCase = true) ||
                carrera.ubicacion.contains(textoBusqueda, ignoreCase = true)

        val coincideFiltro = filtroSeleccionado == "Todas" ||
                (kmFiltro != null && carrera.distanciasDisponiblesKm.contains(kmFiltro))

        coincideBusqueda && coincideFiltro
    }
}

// ---------- CONTENIDO ----------

@Composable
fun ExploreScreenContent(
    verCarreraButtonPressed: (Int) -> Unit,
    carreras: List<Carrera>,
    filtroDistanciaInicial: Int? = null,
    modifier: Modifier = Modifier
) {
    var textoBusqueda by remember { mutableStateOf("") }
    var filtroSeleccionado by remember(filtroDistanciaInicial) {
        mutableStateOf(filtroDistanciaInicial?.let { "${it}K" } ?: "Todas")
    }
    var filtrosVisibles by remember(filtroDistanciaInicial) {
        mutableStateOf(filtroDistanciaInicial != null)
    }

    val carrerasFiltradas = remember(carreras, textoBusqueda, filtroSeleccionado) {
        filtrarCarreras(carreras, textoBusqueda, filtroSeleccionado)
    }

    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderExplorar()
        Spacer(modifier = Modifier.height(16.dp))
        BusquedaYFiltros(
            textoBusqueda = textoBusqueda,
            onTextoBusquedaChange = { textoBusqueda = it },
            filtrosVisibles = filtrosVisibles,
            onToggleFiltros = { filtrosVisibles = !filtrosVisibles }
        )
        AnimatedVisibility(
            visible = filtrosVisibles,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                Spacer(modifier = Modifier.height(14.dp))
                FiltrosRow(
                    filtroSeleccionado = filtroSeleccionado,
                    onFiltroSeleccionado = { filtroSeleccionado = it }
                )
            }
        }
        SeccionTitulo(
            stringResource(R.string.carreras_cerca_de_ti),
            modifier = Modifier.padding(horizontal = 0.dp)
        )
        GrillaCarreras(
            verCarreraButtonPressed = verCarreraButtonPressed,
            carreras = carrerasFiltradas,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ExploreScreen(
    verCarreraButtonPressed: (Int) -> Unit,
    filtroDistanciaInicial: Int? = null,
    modifier: Modifier = Modifier
) {
    val carreras = LocalCarreraProvider.listCarrera
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ExploreScreenContent(
            verCarreraButtonPressed = verCarreraButtonPressed,
            carreras = carreras,
            filtroDistanciaInicial = filtroDistanciaInicial,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ExploreScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ExploreScreen({})
    }
}