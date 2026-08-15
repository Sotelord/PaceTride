package com.example.pacetride.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.R

@Composable
fun LogoApp(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo Pacetride",
        modifier = modifier
    )
}

@Composable
@Preview
fun LogoAppPreview(modifier: Modifier = Modifier) {
    LogoApp()
}