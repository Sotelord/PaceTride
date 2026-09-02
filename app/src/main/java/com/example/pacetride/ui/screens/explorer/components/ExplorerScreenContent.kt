package com.example.pacetride.ui.screens.explorer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.screens.explorer.components.content.GrillaCarreras
import com.example.pacetride.ui.screens.explorer.components.floatingButton.FloatingButtonComunidad
import com.example.pacetride.ui.screens.explorer.components.header.HeaderExplorar
import com.example.pacetride.ui.screens.explorer.components.searchBar.BusquedaYFiltros
import com.example.pacetride.ui.screens.explorer.components.searchBar.FiltrosRow
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.SeccionTitulo


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


@Composable
fun ExploreScreenContent(
    modifier: Modifier = Modifier,
    verCarreraButtonPressed: (Int) -> Unit,
    carreras: List<Carrera>,
    comunidadPressed: () -> Unit,
    textoBusqueda: String,
    onTextoBusquedaChange: (String) -> Unit,
    filtroSeleccionado: String,
    onFiltroSeleccionadoChange: (String) -> Unit,
    filtrosVisibles: Boolean,
    onToggleFiltros: () -> Unit
) {
    val carrerasFiltradas = remember(carreras, textoBusqueda, filtroSeleccionado) {
        filtrarCarreras(carreras, textoBusqueda, filtroSeleccionado)
    }
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HeaderExplorar()
            Spacer(modifier = Modifier.height(16.dp))
            BusquedaYFiltros(
                textoBusqueda = textoBusqueda,
                onTextoBusquedaChange = onTextoBusquedaChange,
                filtrosVisibles = filtrosVisibles,
                onToggleFiltros = onToggleFiltros
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
                        onFiltroSeleccionado = onFiltroSeleccionadoChange
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
        FloatingButtonComunidad(
            onClick = comunidadPressed,
            modifier = Modifier.padding(16.dp).align(Alignment.BottomEnd)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ExploreScreenContentPreview(){
    val carreras = LocalCarreraProvider.listCarrera
    PacetrideTheme(darkTheme = true) {
        ExploreScreenContent(
            carreras = carreras,
            comunidadPressed = {},
            filtroSeleccionado = "",
            filtrosVisibles = true,
            onFiltroSeleccionadoChange = {},
            onTextoBusquedaChange = {},
            onToggleFiltros = {},
            textoBusqueda = "",
            verCarreraButtonPressed = {}
        )
    }
}