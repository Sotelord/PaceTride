package com.example.pacetride.ui.screens.raceDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.CarreraRepository
import com.example.pacetride.data.repository.ResenaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RaceDetailViewModel @Inject constructor(
    private val carreraRepository: CarreraRepository,
    private val authRepository: AuthRepository,
    private val resenaRepository: ResenaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RaceDetailState())
    val uiState: StateFlow<RaceDetailState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(sesion = authRepository.currentUser?.uid ?: "") }
        }
    }

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

    fun sendOrDeleteResenaLike(resenaId: String, userId: String) {
        viewModelScope.launch {
            val result = resenaRepository.sendOrDeleteResenaLike(resenaId, userId)
            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        resenas = state.resenas.map { resena ->
                            if (resena.id == resenaId) {
                                resena.copy(
                                    liked = !resena.liked,
                                    likes = if (resena.liked) resena.likes - 1 else resena.likes + 1
                                )
                            } else {
                                resena
                            }
                        }
                    )
                }
            } else {
                Log.e("LIKE", "Error al dar like", result.exceptionOrNull())
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