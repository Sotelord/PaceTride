package com.example.pacetride.ui.screens.profileEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pacetride.ui.screens.profileEdit.components.ProfileEditScreenContent

@Composable
fun ProfileEditScren(
    modifier: Modifier = Modifier,
    profileEditViewModel: ProfileEditViewModel,
    atrasPressed: () -> Unit
){
    val state by profileEditViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        profileEditViewModel.getUsuario()
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.errorMessage ?: "Error desconocido")
            }
        }

        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                ProfileEditScreenContent(
                    bio = state.bio,
                    onBioChange = { profileEditViewModel.updateBio(it) },
                    ubicacion = state.ubicacion,
                    onUbicacionChange = { profileEditViewModel.updateUbicacion(it) },
                    atrasPressed = atrasPressed,
                    onGuardarClick = { profileEditViewModel.updateUsuario() }
                )
            }
        }
    }
}
