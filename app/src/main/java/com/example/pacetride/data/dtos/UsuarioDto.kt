package com.example.pacetride.data.dtos

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario

data class EstadisticasGlobalesDto(
    val distancia: String, //221,8 km
    val numCarrera: Int, //15
    val mejorTiempo10k: String, //49:47
    val mejorTiempo21k: String, //1:56:12
)

data class UsuarioDto(
    val id: Int, //4
    val nombre: String, //Juan Angarita
    val usuario: String, //@juanangarita
    val email: String, //juan.angarita@example.com
    val ubicacion: String, //Bogotá, Colombia
    val bio: String, //Runner • Disfrutando cada kilómetro y cada nueva ruta 🏃‍♂️🌄
    val fotoPerfil: String?, //null
    val estadisticasGlobales: EstadisticasGlobalesDto
)

fun UsuarioDto.toUsuario(): Usuario {
    return Usuario(
        id = id.toString(),
        bio = bio,
        email = email,
        nombre = nombre,
        usuario = usuario,
        ubicacion = ubicacion,
        fotoPerfil = fotoPerfil,
        estadisticasGlobales = EstadisticasGlobales(
            numCarrera = estadisticasGlobales.numCarrera,
            distacia = estadisticasGlobales.distancia,
            mejorTiempo10k = estadisticasGlobales.mejorTiempo10k,
            mejorTiempo21k = estadisticasGlobales.mejorTiempo21k
        )
    )
}