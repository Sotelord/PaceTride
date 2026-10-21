package com.example.pacetride.ui.screens.comunidad

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.local.LocalPublicacionProvider
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.ResenaRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ComunidadViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioRepository: UsuarioRepository,
    private val resenaRepository: ResenaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ComunidadState())
    val uiState: StateFlow<ComunidadState> = _uiState

    init {
        _uiState.update { it.copy(sesion = authRepository.currentUser?.uid ?: "") }
        getUser()
        getResenas()
    }

    private fun getUser() {
        val uid = authRepository.currentUser?.uid ?: return
        viewModelScope.launch {
            usuarioRepository.getUsuarioById(uid).onSuccess { usuario ->
                val foto = authRepository.currentUser?.photoUrl?.toString() ?: usuario.fotoPerfil
                _uiState.update { it.copy(usuario = usuario.copy(fotoPerfil = foto)) }
            }
        }
    }

    private fun getResenas() {
        /*viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = if (_uiState.value.tabSeleccionada == 0)
                resenaRepository.getAllResenas()
            else
                resenaRepository.getResenasSeguidos()

            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        resenas = result.getOrNull() ?: emptyList(),
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        errorMessage = result.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
            }
        }*/
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            resenaRepository.getResenasLive()
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            errorMessage = e.message,
                            isLoading = false
                        )
                    }
                }
                .collect { resenas ->
                    _uiState.update {
                        it.copy(
                            resenas = resenas,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun selectTab(index: Int) {
        if (index == _uiState.value.tabSeleccionada) return
        _uiState.update { it.copy(tabSeleccionada = index, resenas = emptyList()) }
        getResenas()
    }

    fun updateTextoBusqueda(input: String) {
        _uiState.update { it.copy(textoBusqueda = input) }
    }

    fun sendOrDeleteResenaLike(resenaId: String, userId: String) {
        viewModelScope.launch {
            val result = resenaRepository.sendOrDeleteResenaLike(resenaId, userId)
            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        resenas = state.resenas.map { resena ->
                            if (resena.id == resenaId) {
                                resena.copy(
                                    liked = !resena.liked,
                                    likes = if (resena.liked) resena.likes - 1 else resena.likes + 1
                                )
                            } else resena
                        }
                    )
                }
            } else {
                Log.e("LIKE", "Error al dar like", result.exceptionOrNull())
            }
        }
    }
}