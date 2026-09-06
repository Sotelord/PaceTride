package com.example.pacetride.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.data.local.LocalUsuarioProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> =_uiState

    fun getUser(){
        _uiState.update { it.copy(usuario = LocalUsuarioProvider.usuarios[2]) }
    }

    fun getFeaturedRace() {
        _uiState.update { it.copy(featuredRace = LocalCarreraProvider.listCarrera[2]) }
    }

    init {
        getUser()
        getFeaturedRace()
    }
}