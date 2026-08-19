package com.example.pacetride.data.local

import com.example.pacetride.R
import com.example.pacetride.data.CarreraExplorar

object LocalCarreraExplorarProvider {
    val carreras = listOf(
        CarreraExplorar(
            idImagen = R.drawable.running,
            titulo = "Carrera Atlética Bogotá 10K",
            ubicacion = "Bogotá",
            fecha = "15 de agosto",
            distancia = "10K",
            precio = "$90.000 COP"
        ),
        CarreraExplorar(
            idImagen = R.drawable.running,
            titulo = "Corre por Bogotá 5K",
            ubicacion = "Bogotá",
            fecha = "30 de agosto",
            distancia = "5K",
            precio = "$65.000 COP"
        ),
        CarreraExplorar(
            idImagen = R.drawable.running,
            titulo = "Media Maratón Bogotá 2026",
            ubicacion = "Bogotá",
            fecha = "27 de septiembre",
            distancia = "21K",
            precio = "$145.000 COP"
        ),
        CarreraExplorar(
            idImagen = R.drawable.running,
            titulo = "Correra 0 Bogotá",
            ubicacion = "Bogotá",
            fecha = "27 de agosto",
            distancia = "",
            ultimosCupos = true
        )
    )
}