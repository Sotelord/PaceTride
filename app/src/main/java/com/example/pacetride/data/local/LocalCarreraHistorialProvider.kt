package com.example.pacetride.data.local

import com.example.pacetride.data.CarreraHistorial

object LocalCarreraHistorialProvider {
    val historial = listOf(
        CarreraHistorial(
            titulo = "Carrera 10K Bogotá",
            resultado = "10K • 49:12",
            fecha = "15 de junio de 2026"
        ),
        CarreraHistorial(
            titulo = "Carrera Universitaria Javeriana",
            resultado = "5K • 23:48",
            fecha = "20 de mayo de 2026"
        ),
        CarreraHistorial(
            titulo = "Bogotá Night Run",
            resultado = "10K • 50:31",
            fecha = "18 de abril de 2026"
        )
    )
}