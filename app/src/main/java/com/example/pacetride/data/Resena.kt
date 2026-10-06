package com.example.pacetride.data

data class Resena(
    val usuario: String?,
    val carrera: String?,
    val id: String,
    val resena: String,
    val calificacion: String,
    val categoriasDestacadas: List<String>?,
    val likes: Int,
    val fechaPublicacion: String,
    val usuarioId: String,
    val carreraId: String,
    val fotoUsuario: String? = "",
)
