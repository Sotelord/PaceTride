package com.example.pacetride.ui.screens.profile

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario

data class ProfileState(
    val usuario: Usuario = Usuario(
        id = 10,
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
        )
    )
)