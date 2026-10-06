package com.example.pacetride.data.dtos

import com.example.pacetride.data.Carrera

data class CarreraDto(
    val id: Int, //10
    val raceImageUrl: String?, //null o URL
    val nombre: String, //Carrera Recreativa Parque Simón Bolívar 3K
    val fecha: String, //2026-10-18
    val ubicacion: String, //Parque Simón Bolívar
    val distanciasDisponiblesKm: List<Int>, //[3]
    val precioBase: Int, //40000
    val distanciaReferenciaKm: Int, //3
    val ultimosCupos: Boolean, //false
    val descripcion: String, //Una carrera corta y familiar alrededor del lago del parque. Perfecta para niños, principiantes y quienes quieren dar sus primeros pasos en el running.
    val createdAt: String, //2026-10-03T19:31:37.551Z
    val updatedAt: String //2026-10-03T19:31:37.551Z
)

fun CarreraDto.toCarrera(): Carrera {
    return Carrera (
        raceImageUrl = raceImageUrl ?: "",
        id = id.toString(),
        nombre = nombre,
        fecha = fecha,
        ubicacion = ubicacion,
        distanciasDisponiblesKm = distanciasDisponiblesKm,
        precioBase = precioBase,
        distanciaReferenciaKm = distanciaReferenciaKm,
        ultimosCupos = ultimosCupos,
        descripcion = descripcion,
    )
}
