package com.example.pacetride.ui.screens.conexiones

import com.example.pacetride.data.Usuario

data class ConexionesState(
    val usuarios: List<Usuario> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
