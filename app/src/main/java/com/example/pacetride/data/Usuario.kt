package com.example.pacetride.data

data class Usuario(
    val id: Int,
    val nombre: String,
    val usuario: String,
    val email: String,
    val ubicacion: String,
    val bio: String?,
    val fotoPerfil: String? = null,
    val proximaCarreras: List<Carrera> = emptyList(),
    val historial: List<CarreraRealizada> = emptyList(),
    val resenas: List<Resena> = emptyList(),
    val estadisticasGlobales: EstadisticasGlobales
)
