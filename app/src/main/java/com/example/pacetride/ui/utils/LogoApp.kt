package com.example.pacetride.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R

@Composable
fun LogoApp(modifier: Modifier = Modifier) {
    val esOscuro = isSystemInDarkTheme()

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo Pacetride",
        colorFilter = if (esOscuro) {
            null
        } else {
            ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        },
        modifier = modifier
    )
}

@Composable
@Preview(name = "Logo - Oscuro (original)")
fun LogoAppPreviewDark() {
    PacetrideTheme(darkTheme = true) {
        LogoApp()
    }
}

@Composable
@Preview(name = "Logo - Claro (con filtro)")
fun LogoAppPreviewLight() {
    PacetrideTheme(darkTheme = false) {
        LogoApp()
    }
}