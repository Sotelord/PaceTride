package com.example.pacetride.data.dtos

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class CreateResenaUsuarioDto(
    val fotoPerfil: String? = null,
    val nombre: String? = null,
    val usuario: String? = null,
)

data class CreateResenaCarreraDto(
    val nombre: String,
)

data class CreateResenaDto(
    var usuarioId: String,
    val carreraId: String,
    val resena: String,
    val calificacion: Float,
    val categoriasDestacadas: List<String>?,
    var usuario: CreateResenaUsuarioDto? = null,
    val carrera: CreateResenaCarreraDto? = null,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

data class UpdateResenaDto(
    val resena: String,
    val calificacion: Float,
    val categoriasDestacadas: List<String>?
)