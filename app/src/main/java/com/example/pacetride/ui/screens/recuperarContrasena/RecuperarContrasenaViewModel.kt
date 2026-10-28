package com.example.pacetride.ui.screens.recuperarContrasena

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecuperarContrasenaViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(RecuperarContrasenaState())
    val uiState: StateFlow<RecuperarContrasenaState> = _uiState

    fun updateEmail(input: String) {
        _uiState.update { it.copy(email = input, mensaje = null) }
    }

    fun enviarCorreo() {
        val email = _uiState.value.email.trim()
        if (email.isEmpty() || !email.contains("@")) {
            _uiState.update { it.copy(mensaje = "Ingresa un correo válido", exito = false) }
            return
        }
        viewModelScope.launch {
            val result = authRepository.sendPasswordResetEmail(email)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        mensaje = "Si el correo está registrado, te enviamos un enlace para restablecer tu contraseña. Revisa tu bandeja de entrada y el spam",
                        exito = true
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        mensaje = result.exceptionOrNull()?.message ?: "No pudimos enviar el correo",
                        exito = false
                    )
                }
            }
        }
    }
}