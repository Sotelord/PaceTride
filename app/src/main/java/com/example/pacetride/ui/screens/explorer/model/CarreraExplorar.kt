package com.example.pacetride.ui.screens.explorer.model

data class CarreraExplorar(
    val idImagen: Int,
    val titulo: String,
    val ubicacion: String,
    val fecha: String,
    val distancia: String,
    val precio: String? = null,
    val ultimosCupos: Boolean = false
)