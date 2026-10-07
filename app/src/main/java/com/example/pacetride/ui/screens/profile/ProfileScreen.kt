package com.example.pacetride.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.profile.components.ProfileScreenContent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userId: String,
    profileViewModel: ProfileViewModel,
    editProfilePressed: () -> Unit,
    configurationPressed: () -> Unit,
    logOutPressed: () -> Unit,
    onClickEdit: (String, String) -> Unit,
    onClickCarrera: (String) -> Unit,
) {
    val state by profileViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getUser(userId)
        profileViewModel.getReviewsUsuarioId(userId)
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
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                ProfileScreenContent(
                    usuario = state.usuario,
                    resenas = state.resenas,
                    editProfilePressed = editProfilePressed,
                    configurationPressed = configurationPressed,
                    logOutPressed = {
                        profileViewModel.logOut()
                        logOutPressed()
                    },
                    onPickImg = { profileViewModel.uploadImageToFirebase(it) },
                    modifier = Modifier.weight(1f),
                    onClickEdit = onClickEdit,
                    onClickCarrea = onClickCarrera,
                    onClickDelete = { resenaId -> profileViewModel.deleteResena(resenaId)}
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ProfileScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        ProfileScreen(
            userId = "1",
            profileViewModel = viewModel(),
            editProfilePressed = {},
            configurationPressed = {},
            logOutPressed = {},
            onClickEdit = { _, _ -> },
            onClickCarrera = {}
        )
    }
}