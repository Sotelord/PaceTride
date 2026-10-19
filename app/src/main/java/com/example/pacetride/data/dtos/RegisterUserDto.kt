package com.example.pacetride.data.dtos

data class RegisterUserDto(
    val nombre: String,
    val userName: String,
    val bio: String? = null,
    val ubicacion: String? = null,
    val fotoPerfil: String? = null,
    val estadisticasGlobales: EstadisticasGlobalesDto,
)

data class UpdateUserDto(
    val bio: String? = null,
    val ubicacion: String? = null,
    /*val fotoPerfil: String? = null,
    val estadisticasGlobales: EstadisticasGlobalesDto,*/
)
