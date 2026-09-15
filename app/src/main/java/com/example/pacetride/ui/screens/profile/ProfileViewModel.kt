package com.example.pacetride.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState

    fun getUser() {
        val usuarioLocal = LocalUsuarioProvider.usuarios[2]
        val emailSesion = authRepository.currentUser?.email ?: usuarioLocal.email
        val fotoSesion = authRepository.currentUser?.photoUrl?.toString() ?: usuarioLocal.fotoPerfil
        _uiState.update {
            it.copy(
                usuario = usuarioLocal.copy(
                    email = emailSesion,
                    fotoPerfil = fotoSesion
                )
            )
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

    fun logOut(){
        authRepository.signOut()
    }

    init {
        getUser()
    }
}