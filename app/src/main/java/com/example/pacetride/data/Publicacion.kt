package com.example.pacetride.data

import androidx.annotation.DrawableRes

data class Publicacion(
    @DrawableRes val idAvatar: Int?,
    val id: Int,
    val nombre: String,
    val tiempo: String,
    val texto: String? = null,
    val likes: String,
    val comentarios: String,
    val estadisiticas: EstadisticasCarrera? = null,
    val resena: Resena? = null
) {
    init {
        require(!texto.isNullOrBlank() || !resena?.resena.isNullOrBlank()){
            "La publicación debe tener texto o reseña con contenido"
        }
    }

    val textoAMostrar: String
        get() = resena?.resena?.takeIf { it.isNotBlank() }
            ?: texto!!
}