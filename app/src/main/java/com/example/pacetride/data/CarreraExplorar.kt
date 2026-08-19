package com.example.pacetride.data

import androidx.annotation.DrawableRes

data class CarreraExplorar(
    @DrawableRes val idImagen: Int,
    val titulo: String,
    val ubicacion: String,
    val fecha: String,
    val distancia: String,
    val precio: String? = null,
    val ultimosCupos: Boolean = false
)