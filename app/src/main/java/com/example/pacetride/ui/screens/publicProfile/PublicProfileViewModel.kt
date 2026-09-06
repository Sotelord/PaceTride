package com.example.pacetride.ui.screens.publicProfile

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalUsuarioProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PublicProfileViewModel @Inject constructor(): ViewModel() {
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