package com.example.pacetride.ui.screens.escribirResena

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalCarreraProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class EscribirResenaViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EscribirResenaState())
    val uiState: StateFlow<EscribirResenaState> = _uiState

    fun getCarrera(){
        _uiState.update { it.copy(carrera = LocalCarreraProvider.listCarrera[2]) }
    }

    fun updateCalificacion(input: Int){
        _uiState.update { it.copy(calificacion = input) }
    }

    fun updateTextoResena(input: String){
        _uiState.update { it.copy(textoResena = input) }
    }

    fun toggleDestacar(opcion: String) {
        _uiState.update {
            val actuales = it.seleccionadas
            it.copy(seleccionadas = if (opcion in actuales) actuales - opcion else actuales + opcion)
        }
    }

    fun publicarButtonPressed(){
        if (_uiState.value.textoResena.isBlank()){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "La reseña está vacía, por ende no se puede publicar") }
        } else if (_uiState.value.textoResena.length > 500){
            _uiState.update { it.copy(mostrarMensajeError = true, errorMessage = "La reseña supera los 500 caracteres permitidos") }
        } else {
            _uiState.update { it.copy(navigate = true) }
        }
    }

    init {
        getCarrera()
    }
}