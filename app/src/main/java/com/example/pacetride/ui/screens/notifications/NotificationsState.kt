package com.example.pacetride.ui.screens.notifications

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Notificacion
import com.example.pacetride.data.Usuario

data class NotificationsState(
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
    val notificaciones: List<Notificacion> = emptyList()
)
