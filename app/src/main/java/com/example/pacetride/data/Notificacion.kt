package com.example.pacetride.data

import java.time.LocalDateTime

data class Notificacion(
    val usuario: Usuario? = null,
    val carrera: Carrera? = null,
    val action: String,
    val subtext: String? = null,
    val buttonText: String? = null,
    val hasHeart: Boolean = false,
    val fecha: LocalDateTime
)
