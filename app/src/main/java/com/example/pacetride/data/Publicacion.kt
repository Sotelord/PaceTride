package com.example.pacetride.data

import androidx.annotation.DrawableRes

data class Publicacion(
    @DrawableRes val idAvatar: Int,
    val nombre: String,
    val tiempo: String,
    val texto: String,
    val distancia: String? = null,
    val tiempoCarrera: String? = null,
    val ritmo: String? = null,
    val calificacion: String? = null,
    val likes: String,
    val comentarios: String
)