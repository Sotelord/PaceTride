package com.example.pacetride.ui.screens.home

import com.example.pacetride.data.Carrera
import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario

data class HomeState(
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
    ),
    val featuredRace: Carrera = Carrera(
        id = 10,
        nombre = "Carrera si",
        fecha = "10-10-2010",
        ubicacion = "Allá",
        precioBase = 10,
        descripcion = "Si si"
    )
)
