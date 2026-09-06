package com.example.pacetride.ui.screens.registrar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
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
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState

    fun updateName(input: String){
        _uiState.update { it.copy(name = input) }
    }

    fun updateEmail(input: String){
        _uiState.update { it.copy(email = input) }
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
        if(
            _uiState.value.name.isNullOrEmpty() ||
            _uiState.value.email.isNullOrEmpty() ||
            _uiState.value.password.isNullOrEmpty() ||
            _uiState.value.confirmPassword.isNullOrEmpty()
        ){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Todos los campos deben ser rellenados") }
        } else {
            if(_uiState.value.password.length < 7){
                _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "La contraseña debe tener más de 7 dígitos") }
            } else {
                if(!_uiState.value.email.contains("@")){
                    _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "El correo debe de contener el caracter @") }
                } else {
                    if(_uiState.value.email == "admin@admin.com"){
                        _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "El correo ya esta en uso") }
                    } else {
                        if(_uiState.value.password != _uiState.value.confirmPassword){
                            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Las contraseñas no coinciden") }
                        } else {
                            viewModelScope.launch {
                                try {
                                    authRepository.signUp(_uiState.value.email, _uiState.value.password)
                                    _uiState.update { it.copy(navigate = true) }
                                } catch (e: Exception) {
                                    _uiState.update {
                                        it.copy(
                                            errorMessage = e.message.toString(),
                                            mostrarMensajeError = true
                                        )
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