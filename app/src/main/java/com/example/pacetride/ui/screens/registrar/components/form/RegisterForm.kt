package com.example.pacetride.ui.screens.registrar.components.form

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun RegisterForm(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordVisibleChange: (Boolean) -> Unit,
    termsAccepted: Boolean,
    mostrarMensajeError: Boolean,
    errorMessage: String,
    onTermsAcceptedChange: (Boolean) -> Unit,
    registerPressed: () -> Unit,
    loginPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(modifier = modifier.fillMaxWidth()) {
        // Campo: Nombre Completo
        FormTextField(
            value = name,
            onValueChange = onNameChange,
            label = "Nombre completo",
            colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Correo Electrónico
        FormTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Correo electrónico",
            colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Contraseña
        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Contraseña",
            isVisible = passwordVisible,
            onVisibilityChange = onPasswordVisibleChange,
            colors = fieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Confirmar Contraseña
        PasswordTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmar contraseña",
            isVisible = confirmPasswordVisible,
            onVisibilityChange = onConfirmPasswordVisibleChange,
            colors = fieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Componente: Checkbox de Términos
        TermsCheckbox(
            checked = termsAccepted,
            onCheckedChange = onTermsAcceptedChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        if(mostrarMensajeError)
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Principal de Registro
        Button(
            onClick = {
                registerPressed()
                Log.d("RegisterScreen", "Crear cuenta clicked")
            },
            enabled = termsAccepted,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "CREAR CUENTA", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Componente: Divisor, Google Login y Navegación
        SocialAndNavigationButtons(
            onGoogleLoginPressed = { /* TODO: login con Google */ },
        )
        TextButton(
            onClick = loginPressed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "¿Ya tienes una cuenta? Iniciar sesión",
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterFormPreview() {
    PacetrideTheme(darkTheme = true) {
        RegisterForm(
            name = "",
            onNameChange = {},
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            confirmPassword = "",
            onConfirmPasswordChange = {},
            passwordVisible = false,
            onPasswordVisibleChange = {},
            confirmPasswordVisible = false,
            onConfirmPasswordVisibleChange = {},
            termsAccepted = false,
            onTermsAcceptedChange = {},
            mostrarMensajeError = true,
            errorMessage = "hola",
            registerPressed = {},
            loginPressed = {}
        )
    }
}