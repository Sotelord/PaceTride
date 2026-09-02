package com.example.pacetride.ui.screens.explorer

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.screens.escribirResena.EscribirResenaState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ExploreViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ExploreState())
    val uiState: StateFlow<ExploreState> = _uiState

    fun getCarreras(){
        _uiState.update { it.copy(carreras = LocalCarreraProvider.listCarrera) }
    }

    fun updateTextoBusqueda(input: String){
        _uiState.update { it.copy(textoBusqueda = input) }
    }

    fun updateFiltroSeleccionado(input: String) {
        _uiState.update { it.copy(filtroSeleccionado = input) }
    }

    fun toggleFiltrosVisibles() {
        _uiState.update { it.copy(filtrosVisibles = !it.filtrosVisibles) }
    }

    fun inicializarConFiltro(km: Int?) {
        _uiState.update {
            it.copy(
                filtroSeleccionado = km?.let { valor -> "${valor}K" } ?: "Todas",
                filtrosVisibles = km != null
            )
        }
    }

    init {
        getCarreras()
    }
}