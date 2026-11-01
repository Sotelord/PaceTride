package com.example.pacetride.ui.utils.seguimiento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ContadoresSeguimiento(
    followersCount: Int,
    followingCount: Int,
    onSeguidoresPressed: () -> Unit,
    onSeguidosPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ContadorItem(followersCount, "Seguidores", onSeguidoresPressed)
        ContadorItem(followingCount, "Siguiendo", onSeguidosPressed)
    }
}

@Composable
@Preview
fun ContadoresSeguimientoPreview() {
    PacetrideTheme(darkTheme = true) {
        ContadoresSeguimiento(
            followersCount = 10,
            followingCount = 20,
            onSeguidoresPressed = {},
            onSeguidosPressed = {}
        )
    }
}