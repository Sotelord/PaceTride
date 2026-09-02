package com.example.pacetride.ui.screens.misCarreras

import com.example.pacetride.data.Carrera
import com.example.pacetride.data.CarreraRealizada

data class MisCarrerasState(
    val usuarioId: Int = 0,
    val proximas: List<Carrera> = emptyList(),
    val completadas: List<CarreraRealizada> = emptyList(),
    val selectedTabIndex: Int = 0
)