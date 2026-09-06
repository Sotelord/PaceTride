package com.example.pacetride.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
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
            _uiState.value.email.isEmpty() ||
            _uiState.value.password.isEmpty()
        ){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "Todos los campos deben ser rellenados") }
        }else{
            viewModelScope.launch {
                try{
                    authRepository.signIn(_uiState.value.email, _uiState.value.password)
                    _uiState.update { it.copy(navigate = true) }
                }catch (e : Exception){
                    val mensaje = when (e) {
                        is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrectos"
                        is FirebaseAuthInvalidUserException -> "No existe una cuenta con ese correo"
                        is FirebaseNetworkException -> "Sin conexión a internet. Revisa tu red"
                        else -> "Ocurrió un error al iniciar sesión. Intenta de nuevo"
                    }
                    _uiState.update {
                        it.copy(
                            errorMessage = mensaje,
                            mostrarMensajeError = true
                        )
                    }
                }
            }
        }
    }
}