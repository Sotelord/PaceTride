package com.example.pacetride.ui.screens.publicProfile

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario

data class PublicProfileState(
     val usuario: Usuario? = Usuario(
        id = 10,
        nombre = "demo",
        usuario = "@demo",
        ubicacion = "Tangamandapio",
        bio = "a",
        estadisticasGlobales = EstadisticasGlobales(
            numCarrera = 10,
            distacia = "10km",
            mejorTiempo10k = "10",
            mejorTiempo21k = "21"
        )
    )
)
