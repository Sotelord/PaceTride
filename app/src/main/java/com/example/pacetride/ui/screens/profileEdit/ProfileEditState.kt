package com.example.pacetride.ui.screens.profileEdit

data class ProfileEditState(
    val ubicacion: String = "",
    val bio: String = "",
    val navigate: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val mostrarMensajeError: Boolean = false
)
