package com.example.pacetride.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.screens.login.components.form.LoginForm
import com.example.pacetride.ui.screens.login.components.header.LoginHeader


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    createAcountPressed: () -> Unit,
    recuperarContrasenaPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by loginViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginHeader()
        Spacer(modifier = Modifier.height(32.dp))
        LoginForm(
            email = state.email,
            onEmailChange = {loginViewModel.updateEmail(it)},
            password = state.password,
            onPasswordChange = {loginViewModel.updatePassword(it)},
            passwordVisible = state.passwordVisible,
            onPasswordVisibleChange = {loginViewModel.mostrarEsconderPasswordVisible()},
            loginButtonPressed = { loginViewModel.loginButtonPressed() },
            createAcountPressed = createAcountPressed,
            recuperarContrasenaPressed = recuperarContrasenaPressed,
            mostrarMensajeError = state.mostrarMensajeError,
            errorMessage = state.errorMessage,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PacetrideTheme(darkTheme = true) {
        LoginScreen(
            loginViewModel = viewModel(),
            createAcountPressed = {},
            recuperarContrasenaPressed = {}
        )
    }
}