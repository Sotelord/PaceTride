package com.example.pacetride.data

data class ProfileUiState(
    val nombre: String = "",
    val usuario: String = "",
    val ubicacion: String = "",
    val bio: String = "",
    val fotoPerfil: Int,
    val proximaCarreraTitulo: String = "",
    val proximaCarreraFecha: String = "",
    val proximaCarreraDistancia: String = ""
)
