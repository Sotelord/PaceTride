package com.example.pacetride.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalUsuarioProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState

    fun getUser() {
        _uiState.update { it.copy(usuario = LocalUsuarioProvider.usuarios[2]) }
    }

    init {
        getUser()
    }
}