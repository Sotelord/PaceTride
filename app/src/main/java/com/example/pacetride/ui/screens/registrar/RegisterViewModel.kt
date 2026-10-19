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

    fun registerButtonPressed(){
        val state = _uiState.value
        if(
            state.name.isBlank() ||
            state.email.isBlank() ||
            state.password.isBlank() ||
            state.confirmPassword.isBlank() ||
            state.usuario.isBlank()
        ){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Todos los campos obligatorios deben ser rellenados") }
        } else {
            if(_uiState.value.password.length < 7){
                _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "La contraseña debe tener más de 7 dígitos") }
            } else {
                if(!Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()){
                    _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Ingresa un correo válido") }
                } else {
                    if(_uiState.value.password != _uiState.value.confirmPassword){
                        _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Las contraseñas no coinciden") }
                    } else {
                        if(!_uiState.value.termsAccepted){
                            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Debes aceptar los términos y condiciones") }
                        } else {
                            _uiState.value.bio?.length?.let { it ->
                                if(it > 500){
                                    _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "La biografía no puede tener más de 500 caracteres") }
                                } else {
                                    // Registro en Firebase

                                    viewModelScope.launch {
                                        val result = authRepository.signUp(_uiState.value.email, _uiState.value.password)
                                        if (result.isSuccess) {

                                            val userId = authRepository.currentUser?.uid ?: throw Exception("No se pudo obtener el id del usuario")
                                            usuarioRepository.registerUsuario(
                                                nombre = state.name,
                                                userName = state.usuario,
                                                bio = state.bio,
                                                ubicacion = state.ubicacion,
                                                userId = userId
                                            )

                                            _uiState.update { it.copy(navigate = true) }
                                        } else {
                                            val mensaje = result.exceptionOrNull()?.message ?: "Error al registrarse"
                                            _uiState.update { it.copy(errorMessage = mensaje, mostrarMensajeError = true) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}