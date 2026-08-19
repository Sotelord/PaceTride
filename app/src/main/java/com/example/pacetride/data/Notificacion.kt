package com.example.pacetride.data

data class Notificacion(
    val userName: String,
    val action: String,
    val subtext: String? = null,
    val buttonText: String? = null,
    val hasHeart: Boolean? = null
)
