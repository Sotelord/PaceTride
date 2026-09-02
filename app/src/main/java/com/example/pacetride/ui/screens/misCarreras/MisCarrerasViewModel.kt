package com.example.pacetride.ui.screens.misCarreras

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalUsuarioProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MisCarrerasViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MisCarrerasState())
    val uiState: StateFlow<MisCarrerasState> = _uiState

    fun getCarrerasUsuario() {
        val usuario = LocalUsuarioProvider.usuarios[2]
        _uiState.update {
            it.copy(
                usuarioId = usuario.id,
                proximas = usuario.proximaCarreras,
                completadas = usuario.historial
            )
        }
    }

    fun updateSelectedTabIndex(input: Int) {
        _uiState.update { it.copy(selectedTabIndex = input) }
    }

    init {
        getCarrerasUsuario()
    }
}