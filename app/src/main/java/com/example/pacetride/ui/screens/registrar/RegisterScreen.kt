package com.example.pacetride.ui.screens.registrar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.registrar.components.form.RegisterForm
import com.example.pacetride.ui.screens.registrar.components.header.RegisterHeader


@Composable
fun RegisterScreen(
    loginPressed: () -> Unit,
    registerPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RegisterHeader()
        Spacer(modifier = Modifier.height(32.dp))
        RegisterForm(
            registerPressed = registerPressed,
            loginPressed = loginPressed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PacetrideTheme(darkTheme = true) {
         RegisterScreen(
             loginPressed = {},
             registerPressed = {}
         )
     }
}