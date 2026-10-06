package com.example.pacetride.ui.screens.explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.CarreraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val carreraRepository: CarreraRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExploreState())
    val uiState: StateFlow<ExploreState> = _uiState

    fun getCarreras() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = carreraRepository.getCarreras()
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        carreras = result.getOrNull() ?: emptyList(),
                        isLoading = false,
                        errorMessage = null
                    )
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

    fun updateTextoBusqueda(input: String) {
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