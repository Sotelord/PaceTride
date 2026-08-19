package com.example.pacetride.data.local

import com.example.pacetride.R
import com.example.pacetride.data.Publicacion

object LocalPublicacionProvider {
    val publicaciones = listOf(
        Publicacion(
            idAvatar = R.drawable.foto_perfil,
            nombre = "Santiago Rayo",
            tiempo = "Hace 2 h",
            texto = "¡Por fin llegó el día! 🏃 🔥 Mi primera Media Maratón de Bogotá.",
            distancia = "21,1 km",
            tiempoCarrera = "1:52:40",
            ritmo = "Ritmo: 5:20 min/km",
            likes = "128",
            comentarios = "24"
        ),
        Publicacion(
            idAvatar = R.drawable.foto_perfil,
            nombre = "Laura Gómez",
            tiempo = "Hace 5 h",
            texto = "Acabo de correr la Carrera 10K Bogotá. Muy buena organización y la ruta estuvo increíble.",
            calificacion = "4,5/5",
            likes = "67",
            comentarios = "8"
        ),
        Publicacion(
            idAvatar = R.drawable.foto_perfil,
            nombre = "Luis Martínez",
            tiempo = "Hace 8 h",
            texto = "¿Alguien más se anima a correr la Carrera 5K Universitaria este fin de semana?",
            likes = "34",
            comentarios = "5"
        )
    )
}