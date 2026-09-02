package com.example.pacetride.ui.screens.raceDetail

import com.example.pacetride.data.Carrera

data class RaceDatailState(
    val carrera: Carrera? = Carrera(
        id = 10,
        nombre = "Carrera si",
        fecha = "10-10-2010",
        ubicacion = "Allá",
        precioBase = 10,
        descripcion = "Si si"
    ),
    val kmSeleccionado: Int = 0
)
