package com.example.pacetride.ui.screens.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val mostrarMensajeError: Boolean = false,
    val errorMessage: String = "",
    val navigate: Boolean = false,
)
