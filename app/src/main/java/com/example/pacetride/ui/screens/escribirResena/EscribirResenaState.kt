package com.example.pacetride.ui.screens.escribirResena

import com.example.pacetride.data.Carrera

data class EscribirResenaState(
    val carrera: Carrera = Carrera(
        id = 10,
        nombre = "Carrera si",
        fecha = "10-10-2010",
        ubicacion = "Allá",
        precioBase = 10,
        descripcion = "Si si"
    ),
    val calificacion: Int = 4,
    val textoResena: String = "",
    val mostrarMensajeError: Boolean = false,
    val errorMessage: String = "",
    val navigate: Boolean = false,
    val seleccionadas: Set<String> = setOf("Ruta", "Organización", "Ambiente")
)
