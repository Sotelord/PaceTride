package com.example.pacetride.data.local

import com.example.pacetride.data.CarreraRealizada

object LocalCarreraRealizadaProvider {

    private val carreras = LocalCarreraProvider.listCarrera
    val historial = listOf(
        CarreraRealizada(
            carrera = carreras[0],
            ritmo = "5km/h",
            tiempo = "1:47:00"
        ),
        CarreraRealizada(
            carrera = carreras[2],
            ritmo = "7km/h",
            tiempo = "47:00"
        ),
        CarreraRealizada(
            carrera = carreras[4],
            ritmo = "4km/h",
            tiempo = "2:00:00"
        ),
        CarreraRealizada(
            carrera = carreras[5],
            ritmo = "10km/h",
            tiempo = "47:00"
        )
    )
}