package com.example.pacetride.data.dtos

data class CreateResenaDto(
    val usuarioId: Int,
    val carreraId: Int,
    val resena: String,
    val calificacion: Float,
    val categoriasDestacadas: List<String>?
)

data class UpdateResenaDto(
    val resena: String,
    val calificacion: Float,
    val categoriasDestacadas: List<String>?
)