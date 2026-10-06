package com.example.pacetride.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.ResenaRepository
import com.example.pacetride.data.repository.StorageRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository,
    private val usuarioRepository: UsuarioRepository,
    private val resenaRepository: ResenaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState

    fun getUser(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = usuarioRepository.getUsuarioById(id)
            if (result.isSuccess) {
                val usuarioLocal = result.getOrNull()
                if (usuarioLocal != null) {
                    val emailSesion = authRepository.currentUser?.email ?: usuarioLocal.email
                    val fotoSesion = authRepository.currentUser?.photoUrl?.toString() ?: usuarioLocal.fotoPerfil
                    _uiState.update {
                        it.copy(
                            usuario = usuarioLocal.copy(
                                email = emailSesion,
                                fotoPerfil = fotoSesion
                            ),
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

    fun uploadImageToFirebase(uri: Uri) {
        viewModelScope.launch {
            val result = storageRepository.uploadProfileImage(uri)
            if (result.isSuccess) {
                val urlFinal = result.getOrNull()
                _uiState.update {
                    it.copy(usuario = it.usuario.copy(fotoPerfil = urlFinal))
                }
            }
        }
    }

    fun deleteResena(resenaId: String) {
        viewModelScope.launch {
            val result = resenaRepository.deleteResena(resenaId)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    resenas = _uiState.value.resenas.filter { it.id != resenaId }
                )
            }
        }
    }

    fun logOut() {
        authRepository.signOut()
    }
}