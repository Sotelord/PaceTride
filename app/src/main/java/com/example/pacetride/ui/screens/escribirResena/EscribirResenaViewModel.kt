package com.example.pacetride.ui.screens.escribirResena

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.CarreraRepository
import com.example.pacetride.data.repository.ResenaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@HiltViewModel
class EscribirResenaViewModel @Inject constructor(
    private val resenaRepository: ResenaRepository,
    private val carreraRepository: CarreraRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EscribirResenaState())
    val uiState: StateFlow<EscribirResenaState> = _uiState

    fun updateCalificacion(input: Int) {
        _uiState.update { it.copy(calificacion = input) }
    }

    fun updateTextoResena(input: String) {
        _uiState.update { it.copy(textoResena = input) }
    }

    fun toggleDestacar(opcion: String) {
        _uiState.update {
            val actuales = it.seleccionadas
            it.copy(
                seleccionadas = if (actuales?.contains(opcion) == true ) actuales.minus(opcion) else actuales?.plus(
                    opcion
                )
            )
        }
    }

    fun createResena(usuarioId: String, reviewId: String? = null) {
        if (_uiState.value.textoResena.isBlank()) {
            _uiState.update {
                it.copy(
                    mostrarMensajeError = true,
                    errorMessage = "La reseña está vacía, por ende no se puede publicar"
                )
            }
        } else if (_uiState.value.textoResena.length > 500) {
            _uiState.update {
                it.copy(
                    mostrarMensajeError = true,
                    errorMessage = "La reseña supera los 500 caracteres permitidos"
                )
            }
        } else {
            viewModelScope.launch {
                if(reviewId != null) {
                    val result = resenaRepository.updateResena(
                        id = reviewId,
                        resena = _uiState.value.textoResena,
                        calificacion = _uiState.value.calificacion.toString(),
                        categoriasDestacadas = _uiState.value.seleccionadas
                    )
                    if (result.isSuccess) {
                        _uiState.update { it.copy(navigate = true) }
                    } else {
                        _uiState.update { it.copy(errorMessage = result.exceptionOrNull()?.message) }
                    }
                } else {
                    val result = resenaRepository.createResena(
                        usuarioId = usuarioId,
                        carreraId = _uiState.value.carrera.id,
                        resena = _uiState.value.textoResena,
                        calificacion = _uiState.value.calificacion.toString(),
                        categoriasDestacadas = _uiState.value.seleccionadas
                    )
                    if (result.isSuccess) {
                        _uiState.update { it.copy(navigate = true) }
                    } else {
                        _uiState.update { it.copy(errorMessage = result.exceptionOrNull()?.message) }
                    }
                }
            }
        }
    }

    fun getCarreraId(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = carreraRepository.getCarreraById(id)
            if (result.isSuccess) {
                val carrera = result.getOrNull()
                if (carrera != null) {
                    _uiState.update {
                        it.copy(
                            carrera = carrera,
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

    fun getResenaById(idResena: String) {
        viewModelScope.launch {
            val result = resenaRepository.getResenaById(idResena)
            if (result.isSuccess) {
                val resena = result.getOrNull()
                if (resena != null) {
                    _uiState.update {
                        it.copy(
                            textoResena = resena.resena,
                            calificacion = resena.calificacion.toFloatOrNull()?.roundToInt() ?: 0,
                            seleccionadas = resena.categoriasDestacadas
                        )
                    }
                }
            }else {
                Log.e("EDITAR_RESENA", "No se pudo cargar la reseña", result.exceptionOrNull())
            }
        }
    }

}