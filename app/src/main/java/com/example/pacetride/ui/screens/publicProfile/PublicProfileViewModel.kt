package com.example.pacetride.ui.screens.publicProfile

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalUsuarioProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PublicProfileViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PublicProfileState())
    val uiState: StateFlow<PublicProfileState> = _uiState

    fun getUserId(id: Int){
        val usuario = LocalUsuarioProvider.usuarios.find { it.id == id }
        _uiState.update {
            it.copy(
                usuario = usuario
            )
        }
    }
}