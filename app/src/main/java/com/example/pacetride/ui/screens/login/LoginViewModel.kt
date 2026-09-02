package com.example.pacetride.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    fun updateEmail(input: String){
        _uiState.update { it.copy(email = input) }
    }

    fun updatePassword(input: String){
        _uiState.update { it.copy(password = input) }
    }

    fun mostrarEsconderPasswordVisible (){
        val valorActual = _uiState.value.passwordVisible
        _uiState.update { it.copy(passwordVisible = !valorActual) }
    }

    fun loginButtonPressed(){
        if(
            _uiState.value.email.isNullOrEmpty() ||
            _uiState.value.password.isNullOrEmpty()
        ){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Todos los campos deben ser rellenados") }
        }else{
            _uiState.update { it.copy(navigate = true) }
        }
    }
}