package com.example.pacetride.ui.screens.raceDetail

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
class RaceDetailViewModel @Inject constructor(
    private val carreraRepository: CarreraRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RaceDetailState())
    val uiState: StateFlow<RaceDetailState> = _uiState

    fun getRaceId(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = carreraRepository.getCarreraById(id)
            if (result.isSuccess) {
                val carrera = result.getOrNull()
                if (carrera != null) {
                    _uiState.update {
                        it.copy(
                            carrera = carrera,
                            kmSeleccionado = if (it.kmSeleccionado in carrera.distanciasDisponiblesKm)
                                it.kmSeleccionado
                            else
                                carrera.distanciasDisponiblesKm.firstOrNull() ?: carrera.distanciaReferenciaKm,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
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

    fun getResenas(id: String) {
        viewModelScope.launch {
            val result = carreraRepository.getReviewsCarreraId(id)
            if (result.isSuccess) {
                val resenas = result.getOrNull()
                if (resenas != null) {
                    _uiState.update {
                        it.copy(
                            resenas = resenas
                        )
                    }
                }
            }
        }
    }

    fun updateKmSeleccionado(input: Int) {
        _uiState.update { it.copy(kmSeleccionado = input) }
    }

    fun getPrecioActual(): Int {
        val estado = _uiState.value
        return estado.carrera?.calcularPrecio(estado.kmSeleccionado) ?: 0
    }
}