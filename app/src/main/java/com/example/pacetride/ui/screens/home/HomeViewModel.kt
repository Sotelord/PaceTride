package com.example.pacetride.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.CarreraRepository
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val carreraRepository: CarreraRepository,
    private val usuarioRepository: UsuarioRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> =_uiState

    fun getUser(id: String){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = usuarioRepository.getUsuarioById(id)
            if (result.isSuccess) {
                val usuarioLocal = result.getOrNull()
                if (usuarioLocal != null) {
                    _uiState.update {
                        it.copy(
                            usuario = usuarioLocal,
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

    fun getCarreras() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = carreraRepository.getCarreras()
            if (result.isSuccess) {
                val lista = result.getOrNull() ?: emptyList()
                _uiState.update {
                    it.copy(
                        carreras = lista,
                        featuredRace = lista.randomOrNull() ?: it.featuredRace,
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

    init {
        getCarreras()
    }
}