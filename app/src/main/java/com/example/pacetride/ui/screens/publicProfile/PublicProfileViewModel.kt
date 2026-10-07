package com.example.pacetride.ui.screens.publicProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pacetride.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PublicProfileViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PublicProfileState())
    val uiState: StateFlow<PublicProfileState> = _uiState

    fun getUserId(id: String){
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

    fun getReviewsUsuarioId(id: String) {
        viewModelScope.launch {
            val result = usuarioRepository.getReviewsUsuarioId(id)
            if (result.isSuccess) {
                val resenas = result.getOrNull()
                if (resenas != null) {
                    _uiState.update {
                        it.copy(
                            resenas = resenas
                        )
                    }
                    Log.d("Lenght", "VM ${resenas.size}")
                }
            } else {
                Log.e("Lenght", "VM falló", result.exceptionOrNull())
            }
        }
    }
}