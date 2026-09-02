package com.example.pacetride.ui.screens.registrar


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
import com.example.pacetride.ui.screens.registrar.components.form.RegisterForm
import com.example.pacetride.ui.screens.registrar.components.header.RegisterHeader


@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    loginPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by registerViewModel.uiState.collectAsState()

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
            name = state.name,
            onNameChange = {registerViewModel.updateName(it)},
            email = state.email,
            onEmailChange = {registerViewModel.updateEmail(it)},
            password = state.password,
            onPasswordChange = {registerViewModel.updatePassword(it)},
            confirmPassword = state.confirmPassword,
            onConfirmPasswordChange = {registerViewModel.updateConfirmPassword(it)},
            passwordVisible = state.passwordVisible,
            onPasswordVisibleChange = {registerViewModel.mostrarEsconderPasswordVisible()},
            confirmPasswordVisible = state.confirmPasswordVisible,
            onConfirmPasswordVisibleChange = {registerViewModel.mostrarEsconderConfirmPasswordVisible()},
            termsAccepted = state.termsAccepted,
            onTermsAcceptedChange = {registerViewModel.aceptarTerminos()},
            mostrarMensajeError = state.mostrarMensajeError,
            errorMessage = state.errorMessage,
            registerPressed = {registerViewModel.registerButtonPressed()},
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
             registerViewModel = viewModel()
         )
     }
}