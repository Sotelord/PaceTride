package com.example.pacetride.ui.screens.publicProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.publicProfile.components.PublicProfileContent

@Composable
fun PublicProfileScreen(
    publicProfileViewModel: PublicProfileViewModel,
    usuarioId: Int,
    atrasPressed: () -> Unit,
    comentariosPressed: () -> Unit,
    configPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state by publicProfileViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        publicProfileViewModel.getUserId(usuarioId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if(state.usuario != null){
            PublicProfileContent(
                atrasPressed = atrasPressed,
                comentariosPressed = comentariosPressed,
                configPressed = configPressed,
                usuario = state.usuario!!,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 32.dp
                    )
            )
        } else {
            Text("Usuario no encontrados")
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PublicProfileScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        PublicProfileScreen(
            publicProfileViewModel = viewModel(),
            usuarioId =  2,
            atrasPressed = {},
            comentariosPressed = {},
            configPressed = {})
    }
}