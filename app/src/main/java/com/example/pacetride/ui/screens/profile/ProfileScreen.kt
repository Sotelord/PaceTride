package com.example.pacetride.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.profile.components.ProfileScreenContent

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    editProfilePressed: () -> Unit,
    configurationPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by profileViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ProfileScreenContent(
            usuario = state.usuario,
            editProfilePressed = editProfilePressed,
            configurationPressed = configurationPressed,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ProfileScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ProfileScreen(
            profileViewModel = viewModel(),
            editProfilePressed = {},
            configurationPressed = {}
        )
    }
}