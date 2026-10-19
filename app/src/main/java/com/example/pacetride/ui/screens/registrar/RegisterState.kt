package com.example.pacetride.ui.screens.registrar

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val usuario: String = "",
    val ubicacion: String? = null,
    val bio: String? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val termsAccepted: Boolean = false,
    val mostrarMensajeError: Boolean = false,
    val errorMessage: String = "",
    val navigate: Boolean = false
)
