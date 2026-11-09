package com.example.pacetride.ui.screens.mapaResenas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.ResenaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapaResenasViewModel @Inject constructor(
    private val resenaRepository: ResenaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapaResenaState())
    val uiState: StateFlow<MapaResenaState> = _uiState

    fun observeResenasRecientes() {
        viewModelScope.launch {
            resenaRepository.getResenasRecientesConUbicacion()
                .catch { e ->
                    Log.e("RESENAS", "Error al cargar reseñas", e)
                    _uiState.update {
                        it.copy(
                            errorMessage = e.message,
                            isLoading = false
                        )
                    }
                }
                .collect { lista ->
                    _uiState.update { estado ->
                        estado.copy(
                            resenas = lista,
                            isLoading = false,
                            errorMessage = null,
                            resenaSeleccionadaId = estado.resenaSeleccionadaId?.takeIf { id -> lista.any { it.id == id } }
                        )
                    }
                }
        }
    }

    fun seleccionarResena(id: String) {
        _uiState.update { it.copy(resenaSeleccionadaId = id) }
    }

    fun limpiarSeleccion() {
        _uiState.update { it.copy(resenaSeleccionadaId = null) }
    }
}