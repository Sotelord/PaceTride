package com.example.pacetride.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.screens.notifications.components.NotificationsScreenContent
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    notificationsViewModel: NotificationsViewModel,
    usuarioId: Int,
    atrasPressed: () -> Unit,
    viewProfile: (Int) -> Unit,
    verCarrera: (Int) -> Unit = {}
) {
    val state by notificationsViewModel.uiState.collectAsState()

    LaunchedEffect(usuarioId) {
        notificationsViewModel.getNotificaciones(usuarioId)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NotificationsScreenContent(
            notificaciones = state.notificaciones,
            onMarcarComoLeida = { notificationsViewModel.marcarComoLeida(it) },
            onMarcarTodasComoLeidas = { notificationsViewModel.marcarTodasComoLeidas() },
            atrasPressed = atrasPressed,
            viewProfile = viewProfile,
            verCarrera = verCarrera
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun NotificationsScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        NotificationsScreen(
            notificationsViewModel = viewModel<NotificationsViewModel>(),
            usuarioId = 3,
            atrasPressed = {},
            viewProfile = {}
        )
    }
}