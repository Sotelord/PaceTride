package com.example.pacetride.ui.screens.notifications

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalNotificacionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationsState())
    val uiState: StateFlow<NotificationsState> = _uiState

    fun getNotificaciones(usuarioId: Int) {
        _uiState.update {
            it.copy(notificaciones = LocalNotificacionProvider.notificacionesDe(usuarioId))
        }
    }

    fun marcarComoLeida(id: Int) {
        _uiState.update { estado ->
            estado.copy(
                notificaciones = estado.notificaciones.map { notificacion ->
                    if (notificacion.id == id) notificacion.copy(leida = true) else notificacion
                }
            )
        }
    }

    fun marcarTodasComoLeidas() {
        _uiState.update { estado ->
            estado.copy(
                notificaciones = estado.notificaciones.map { it.copy(leida = true) }
            )
        }
    }
}
