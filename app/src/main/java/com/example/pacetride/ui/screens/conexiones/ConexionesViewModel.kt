package com.example.pacetride.ui.screens.conexiones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TipoConexion {
    SEGUIDORES,
    SEGUIDOS
}

@HiltViewModel
class ConexionesViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ConexionesState())
    val uiSstate: StateFlow<ConexionesState> = _uiState

    fun cargar(usuarioId: String, tipo: TipoConexion) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = when (tipo) {
                TipoConexion.SEGUIDORES -> usuarioRepository.getFollowers(usuarioId)
                TipoConexion.SEGUIDOS -> usuarioRepository.getFollowing(usuarioId)
            }
            if (result.isSuccess) {
                _uiState.update { it.copy(usuarios = result.getOrDefault(emptyList()), isLoading = false) }
            } else {
                _uiState.update { it.copy(errorMessage = result.exceptionOrNull()?.message, isLoading = false) }
            }
        }
    }
}