package com.example.pacetride.ui.screens.mapaResenas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun MapaHeader(
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val esOscuro = isSystemInDarkTheme()

    Box (
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Surface(
            modifier = Modifier.align(Alignment.CenterStart),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            IconButton(onClick = atrasPressed) {
                Icon(
                    painter = painterResource(R.drawable.ic_atras),
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Surface(
            modifier = Modifier.align(Alignment.Center),
            shape = RoundedCornerShape(50),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                colorFilter = if (esOscuro) {
                    null
                } else {
                    ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                },
                modifier = Modifier
                    .height(36.dp)
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
@Preview
fun MapaHeaderPreview() {
    PacetrideTheme(darkTheme = true) {
        MapaHeader(atrasPressed = {})
    }
}

@Composable
@Preview
fun MapaHeaderPreview2() {
    PacetrideTheme(darkTheme = false) {
        MapaHeader(atrasPressed = {})
    }
}