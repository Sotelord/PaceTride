package com.example.pacetride.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState

    fun getUser() {
        val usuarioLocal = LocalUsuarioProvider.usuarios[2]
        val emailSesion = authRepository.currentUser?.email ?: usuarioLocal.email
        _uiState.update {
            it.copy(usuario = usuarioLocal.copy(email = emailSesion))
        }
    }

    fun logOut(){
        authRepository.signOut()
    }

    init {
        getUser()
    }
}