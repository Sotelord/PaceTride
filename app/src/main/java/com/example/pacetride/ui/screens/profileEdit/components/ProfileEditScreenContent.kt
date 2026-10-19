package com.example.pacetride.ui.screens.profileEdit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.escribirResena.components.content.BotonPublicarResena
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.AppButton
import com.example.pacetride.ui.utils.BarraSuperiorConTitulo
import com.example.pacetride.ui.utils.forms.FormTextField

@Composable
fun ProfileEditScreenContent(
    modifier: Modifier = Modifier,
    bio: String,
    onBioChange: (String) -> Unit,
    ubicacion: String,
    onUbicacionChange: (String) -> Unit,
    atrasPressed: () -> Unit,
    onGuardarClick: () -> Unit,
) {
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        BarraSuperiorConTitulo(
            titulo = "Modifica tu perfil",
            atrasPressed = atrasPressed
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            FormTextField(
                value = ubicacion,
                onValueChange = onUbicacionChange,
                label = "Ubicación (Opcional)",
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            FormTextField(
                value = bio,
                onValueChange = onBioChange,
                label = "Cuéntanos sobre ti (Opcional)",
                colors = fieldColors,
                maxLength = 500,
                singleLine = false,
                minLines = 4,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        AppButton(
            textoBoton = "Guardar cambios",
            onClick = onGuardarClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ProfileEditScreenContentPreview(){
    PacetrideTheme(darkTheme = true) {
        ProfileEditScreenContent(
            bio = "hola",
            onBioChange = {},
            ubicacion = "hola",
            onUbicacionChange = {},
            atrasPressed = {},
            onGuardarClick = {}
        )
    }
}