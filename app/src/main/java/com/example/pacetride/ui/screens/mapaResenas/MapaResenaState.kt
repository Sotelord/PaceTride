package com.example.pacetride.ui.screens.mapaResenas

import com.example.pacetride.data.Resena

data class MapaResenaState(
    val resenas: List<Resena> = emptyList(),
    val resenaSeleccionadaId: String? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
) {
    val resenaSeleccionada: Resena?
        get() = resenas.find { it.id == resenaSeleccionadaId }
}
