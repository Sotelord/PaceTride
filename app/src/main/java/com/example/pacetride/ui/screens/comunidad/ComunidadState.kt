package com.example.pacetride.ui.screens.comunidad

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Resena
import com.example.pacetride.data.Usuario

data class ComunidadState(
    val usuario: Usuario = Usuario(
        id = "10",
        nombre = "demo",
        usuario = "@demo",
        email = "example0.com",
        ubicacion = "Tangamandapio",
        bio = "a",
        estadisticasGlobales = EstadisticasGlobales(
            numCarrera = 10,
            distacia = "10km",
            mejorTiempo10k = "10",
            mejorTiempo21k = "21"
        ),
        historial = emptyList(),
        resenas = emptyList(),
        followed = false,
        followersCount = 0,
        followingCount = 0
    ),
    val sesion: String = "",
    val resenas: List<Resena> = emptyList(),
    val tabSeleccionada: Int = 0,
    val textoBusqueda: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)