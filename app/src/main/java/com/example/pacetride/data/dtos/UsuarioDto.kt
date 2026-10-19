package com.example.pacetride.data.dtos

import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario
import kotlin.toString

abstract class UsuarioDtoGeneric {
    abstract fun toUsuario(): Usuario
}

data class UsuarioFirestoreDto(
    val id: String, //4
    val nombre: String, //Juan Angarita
    val userName: String, //@juanangarita
    val email: String, //juan.angarita@example.com
    val ubicacion: String?, //Bogotá, Colombia
    val bio: String?, //Runner • Disfrutando cada kilómetro y cada nueva ruta 🏃‍♂️🌄
    val fotoPerfil: String?, //null
    val estadisticasGlobales: EstadisticasGlobalesDto
): UsuarioDtoGeneric() {
    constructor() : this(
        id= "0",
        nombre = "",
        userName = "",
        email = "",
        ubicacion = "",
        bio = "",
        fotoPerfil = null,
        estadisticasGlobales = EstadisticasGlobalesDto(
            distancia = "0km",
            numCarrera = 0,
            mejorTiempo10k = "0:00",
            mejorTiempo21k = "0:00"
        )
    )

    override fun toUsuario(): Usuario {
        return Usuario(
            id = id,
            bio = if (bio == null || bio == "") "No hay biografía" else bio,
            email = email,
            nombre = nombre,
            usuario = userName,
            ubicacion = if (ubicacion == null || ubicacion == "") "No hay ubicación" else ubicacion,
            fotoPerfil = fotoPerfil,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = estadisticasGlobales.numCarrera,
                distacia = estadisticasGlobales.distancia,
                mejorTiempo10k = estadisticasGlobales.mejorTiempo10k,
                mejorTiempo21k = estadisticasGlobales.mejorTiempo21k
            )
        )
    }
}

data class UsuarioRetrofitDto(
    val id: String, //4
    val nombre: String, //Juan Angarita
    val usuario: String, //@juanangarita
    val email: String, //juan.angarita@example.com
    val ubicacion: String?, //Bogotá, Colombia
    val bio: String?, //Runner • Disfrutando cada kilómetro y cada nueva ruta 🏃‍♂️🌄
    val fotoPerfil: String?, //null
    val estadisticasGlobales: EstadisticasGlobalesDto
): UsuarioDtoGeneric() {
    constructor() : this(
        id= "0",
        nombre = "",
        usuario ="",
        email = "",
        ubicacion = "",
        bio = "",
        fotoPerfil = null,
        estadisticasGlobales = EstadisticasGlobalesDto(
            distancia = "0km",
            numCarrera = 0,
            mejorTiempo10k = "0:00",
            mejorTiempo21k = "0:00"
        )
    )

    override fun toUsuario(): Usuario {
        return Usuario(
            id = id,
            bio = if (bio == null || bio == "") "No hay biografía" else bio,
            email = email,
            nombre = nombre,
            usuario = if (ubicacion == null || ubicacion == "") "No hay ubicación" else ubicacion,
            ubicacion = ubicacion ?: "No hay ubicación",
            fotoPerfil = fotoPerfil,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = estadisticasGlobales.numCarrera,
                distacia = estadisticasGlobales.distancia,
                mejorTiempo10k = estadisticasGlobales.mejorTiempo10k,
                mejorTiempo21k = estadisticasGlobales.mejorTiempo21k
            )
        )
    }
}