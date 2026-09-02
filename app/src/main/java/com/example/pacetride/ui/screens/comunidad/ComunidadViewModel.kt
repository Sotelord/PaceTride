package com.example.pacetride.ui.screens.comunidad

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalPublicacionProvider
import com.example.pacetride.data.local.LocalUsuarioProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ComunidadViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ComunidadState())
    val uiState: StateFlow<ComunidadState> = _uiState

    fun getUser(){
        _uiState.update { it.copy(usuario = LocalUsuarioProvider.usuarios[2]) }
    }

    fun getAllPublicaciones(){
        _uiState.update { it.copy(publicaciones = LocalPublicacionProvider.publicaciones) }
    }

    fun updateTextoBusqueda(input: String){
        _uiState.update { it.copy(textoBusqueda = input) }
    }

    init {
        getUser()
        getAllPublicaciones()
    }
}