package com.example.pacetride.data.local

import com.example.pacetride.data.EstadisticasCarrera
import com.example.pacetride.data.Publicacion

object LocalPublicacionProvider {

    private val usuarios = LocalUsuarioProvider.usuarios

    val publicaciones = listOf(
        Publicacion(
            idAvatar = usuarios[0].fotoPerfil,
            nombre = usuarios[0].nombre,
            tiempo = "Hace 2 h",
            texto = "¡Por fin llegó el día! 🏃 🔥 Mi primera Media Maratón de Bogotá.",
            estadisiticas = EstadisticasCarrera(
                distancia = "21,1 km",
                tiempoCarrera = "1:52:40",
                ritmo = "Ritmo: 5:20 min/km"
            ),
            likes = "128",
            comentarios = "24"
        ),

        Publicacion(
            idAvatar = usuarios[1].fotoPerfil,
            nombre = usuarios[1].nombre,
            tiempo = "Hace 5 h",
            resena = LocalResenaProvider.listaResenas[1],
            likes = "67",
            comentarios = "8"
        ),

        Publicacion(
            idAvatar = usuarios[2].fotoPerfil,
            nombre = usuarios[2].nombre,
            tiempo = "Hace 8 h",
            texto = "¿Alguien más se anima a correr la Carrera 5K Universitaria este fin de semana?",
            likes = "34",
            comentarios = "5"
        )
    )
}