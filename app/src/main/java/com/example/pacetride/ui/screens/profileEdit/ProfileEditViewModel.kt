package com.example.pacetride.ui.screens.profileEdit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ProfileEditState())
    val uiState: StateFlow<ProfileEditState> = _uiState

    fun updateUbicacion(input: String) {
        _uiState.update { it.copy(ubicacion = input) }
    }

    fun updateBio(input: String) {
        _uiState.update { it.copy(bio = input) }
    }

    fun updateUsuario(){
        val usuarioId = authRepository.currentUser?.uid ?: return
        val state = _uiState.value
        viewModelScope.launch {
            val result = usuarioRepository.updateUsuario(
                id = usuarioId,
                bio = state.bio,
                ubicacion = state.ubicacion
            )
            if (result.isSuccess) {
                _uiState.update { it.copy(navigate = true) }
            } else {
                _uiState.update { it.copy(errorMessage = result.exceptionOrNull()?.message) }
            }
        }
    }

    fun getUsuario() {
        val usuarioId = authRepository.currentUser?.uid ?: return
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            val result = usuarioRepository.getUsuarioById(usuarioId)
            if (result.isSuccess) {
                val usuario = result.getOrNull()
                if (usuario != null) {
                    _uiState.update {
                        it.copy(
                            bio = if(usuario.bio == "No hay biografía") "" else usuario.bio,
                            ubicacion = if(usuario.ubicacion == "No hay ubicación") "" else usuario.ubicacion,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            } else {
                _uiState.update {
                    it.copy(
                        errorMessage = result.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
                Log.e("EDITAR_PERFIL", "No se pudo cargar el usuario", result.exceptionOrNull())
            }
        }
    }
}