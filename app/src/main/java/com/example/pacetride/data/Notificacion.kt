package com.example.pacetride.data

import java.time.LocalDateTime

data class Notificacion(
    val id: Int,
    val paraUsuarioId: Int,              // a quién le pertenece esta notificación
    val usuario: Usuario? = null,        // quién hizo la acción (el actor)
    val carrera: Carrera? = null,
    val action: String,
    val subtext: String? = null,
    val buttonText: String? = null,
    val hasHeart: Boolean = false,
    val leida: Boolean = false,
    val fecha: LocalDateTime
)