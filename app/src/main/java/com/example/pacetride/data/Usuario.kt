package com.example.pacetride.data

import com.example.pacetride.R

data class Usuario(
    val id: Int,
    val nombre: String,
    val usuario: String,
    val ubicacion: String,
    val bio: String?,
    val fotoPerfil: Int? = R.drawable.ic_perfil,
    val proximaCarreras: List<Carrera> = emptyList(),
    val historial: List<CarreraRealizada> = emptyList(),
    val resenas: List<Resena> = emptyList(),
    val estadisticasGlobales: EstadisticasGlobales
)
