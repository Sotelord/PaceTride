package com.example.pacetride.ui.screens.publicProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.ResenaRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PublicProfileViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val authRepository: AuthRepository,
    private val resenaRepository: ResenaRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PublicProfileState())
    val uiState: StateFlow<PublicProfileState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(sesion = authRepository.currentUser?.uid ?: "") }
        }
    }


    fun getUserId(id: String){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = usuarioRepository.getUsuarioById(id)
            if (result.isSuccess) {
                val usuarioLocal = result.getOrNull()
                if (usuarioLocal != null) {
                    _uiState.update {
                        it.copy(
                            usuario = usuarioLocal,
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
            }
        }
    }

    fun getReviewsUsuarioId(id: String) {
        viewModelScope.launch {
            val result = usuarioRepository.getReviewsUsuarioId(id)
            if (result.isSuccess) {
                val resenas = result.getOrNull()
                if (resenas != null) {
                    _uiState.update {
                        it.copy(
                            resenas = resenas
                        )
                    }
                    Log.d("Lenght", "VM ${resenas.size}")
                }
            } else {
                Log.e("Lenght", "VM falló", result.exceptionOrNull())
            }
        }
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
                            } else {
                                resena
                            }
                        }
                    )
                }
            } else {
                Log.e("LIKE", "Error al dar like", result.exceptionOrNull())
            }
        }
    }

    fun followOrUnfollowUsuario(targetUserId: String) {
        val currentUserId = _uiState.value.sesion
        if (currentUserId.isEmpty() || currentUserId == targetUserId) return
        viewModelScope.launch {
            val result = usuarioRepository.followOrUnfollowUsuario(currentUserId, targetUserId)
            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        usuario = state.usuario?.copy(
                            followed = !state.usuario.followed,
                            followersCount = if (state.usuario.followed) state.usuario.followersCount - 1 else state.usuario.followersCount + 1
                        )
                    )
                }
            } else {
                Log.e("FOLLOW", "Error al seguir/dejar de seguir", result.exceptionOrNull())
            }
        }
    }
}