package com.example.pacetride.ui.screens.raceDetail

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalCarreraProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class RaceDatailViewModel @Inject constructor(): ViewModel() {
    private  val _uiState = MutableStateFlow(RaceDatailState())
    val uiState: StateFlow<RaceDatailState> = _uiState

    fun getRaceId(id: Int){
        //_uiState.update { it.copy(carrera = LocalCarreraProvider.listCarrera.find { it.id == id }) }
        val carrera = LocalCarreraProvider.listCarrera.find { it.id == id }
        _uiState.update {
            it.copy(
                carrera = carrera,
                kmSeleccionado = carrera?.distanciaPrincipalKm ?: 0 
            )
        }
    }

    fun updateKmSeleccionado(input: Int){
        _uiState.update { it.copy(kmSeleccionado = input) }
    }

    fun getPrecioActual(): Int{
        val estado = _uiState.value
        return estado.carrera?.calcularPrecio(estado.kmSeleccionado) ?: 0
    }
}