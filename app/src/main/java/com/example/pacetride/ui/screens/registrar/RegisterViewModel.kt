package com.example.pacetride.ui.screens.registrar

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// - controlar los datos que se muestran en pantalla
// - lógica de negocio
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState

    fun updateName(input: String){
        _uiState.update { it.copy(name = input) }
    }

    fun updateEmail(input: String){
        _uiState.update { it.copy(email = input) }
    }

    fun updateUsuario(input: String){
        _uiState.update { it.copy(usuario = input) }
    }

    fun updateUbicacion(input: String){
        _uiState.update { it.copy(ubicacion = input) }
    }

    fun updateBio(input: String){
        _uiState.update { it.copy(bio = input) }
    }

    fun updatePassword(input: String){
        _uiState.update { it.copy(password = input) }
    }

    fun updateConfirmPassword(input: String){
        _uiState.update { it.copy(confirmPassword = input) }
    }

    fun mostrarEsconderPasswordVisible (){
        val valorActual = _uiState.value.passwordVisible
        _uiState.update { it.copy(passwordVisible = !valorActual) }
    }

    fun mostrarEsconderConfirmPasswordVisible (){
        val valorActual = _uiState.value.confirmPasswordVisible
        _uiState.update { it.copy(confirmPasswordVisible = !valorActual) }
    }

    fun aceptarTerminos (){
        val valorActual = _uiState.value.termsAccepted
        _uiState.update { it.copy(termsAccepted = !valorActual) }
    }

    fun registerButtonPressed() {
        val state = _uiState.value

        val error = when {
            state.name.isBlank() || state.email.isBlank() || state.usuario.isBlank() ||
                    state.password.isBlank() || state.confirmPassword.isBlank() ->
                "Todos los campos obligatorios deben ser rellenados"
            state.password.length < 7 -> "La contraseña debe tener al menos 7 caracteres"
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Ingresa un correo válido"
            state.password != state.confirmPassword -> "Las contraseñas no coinciden"
            !state.termsAccepted -> "Debes aceptar los términos y condiciones"
            (state.bio?.length ?: 0) > 500 -> "La biografía no puede tener más de 500 caracteres"
            else -> null
        }

        if (error != null) {
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = error) }
            return
        }

        viewModelScope.launch {
            val result = authRepository.signUp(state.email, state.password)
            if (result.isFailure) {
                val mensaje = result.exceptionOrNull()?.message ?: "Error al registrarse"
                _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = mensaje) }
            }

            val userId = authRepository.currentUser?.uid
            if (userId == null) {
                _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "No se pudo obtener el id del usuario") }
            } else {
                val registro = usuarioRepository.registerUsuario(
                    nombre = state.name,
                    userName = state.usuario,
                    bio = state.bio,
                    ubicacion = state.ubicacion,
                    userId = userId
                )
                if (registro.isSuccess) {
                    _uiState.update { it.copy(navigate = true) }
                } else {
                    val mensaje = registro.exceptionOrNull()?.message ?: "No pudimos guardar tu perfil"
                    _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = mensaje) }
                }
            }
        }
    }
}