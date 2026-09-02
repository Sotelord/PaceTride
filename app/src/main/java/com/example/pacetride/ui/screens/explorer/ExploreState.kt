package com.example.pacetride.ui.screens.explorer

import com.example.pacetride.data.Carrera

data class ExploreState(
    val carreras: List<Carrera> = emptyList(),
    val textoBusqueda: String = "",
    val filtroSeleccionado: String = "Todas",
    val filtrosVisibles: Boolean = false
)
