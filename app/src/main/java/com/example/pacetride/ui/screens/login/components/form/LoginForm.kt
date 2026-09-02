package com.example.pacetride.ui.screens.login.components.form


import android.util.Log
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.screens.registrar.components.form.FormTextField
import com.example.pacetride.ui.screens.registrar.components.form.PasswordTextField
import com.example.pacetride.ui.screens.registrar.components.form.SocialAndNavigationButtons
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.AppButton

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    loginButtonPressed: () -> Unit,
    createAcountPressed: () -> Unit,
    recuperarContrasenaPressed: () -> Unit,
    mostrarMensajeError: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(modifier = modifier.fillMaxWidth()) {
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

        Spacer(modifier = Modifier.height(8.dp))

        // Enlace: Recuperar Contraseña
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = MaterialTheme.colorScheme.primaryContainer,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    recuperarContrasenaPressed()
                    Log.d("LoginScreen", "Olvido contrasenna clicked")
                }
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(mostrarMensajeError)
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Principal de Login
        AppButton(
            textoBoton = "INICIAR SESIÓN",
            onClick = {
                loginButtonPressed()
                Log.d("LoginScreen", "Iniciar sesión clicked")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Componente inferior de Login Social y Registro
        SocialAndNavigationButtons(
            onGoogleLoginPressed = { /* TODO: Login con Google */ },
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "¿No tienes una cuenta? ", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
            Text(
                text = "Crear cuenta",
                color = MaterialTheme.colorScheme.primaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { createAcountPressed() }
            )
        }
    }
}



@Preview(showBackground =true)
@Composable
fun LoginFormPreview() {
    PacetrideTheme(darkTheme = true) {
        LoginForm(
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            passwordVisible = false,
            onPasswordVisibleChange = {},
            loginButtonPressed = {},
            createAcountPressed = {},
            recuperarContrasenaPressed = {},
            mostrarMensajeError = true,
            errorMessage = "Hola"
        )
    }
}