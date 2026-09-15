package com.example.pacetride.ui.utils

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme

// ---------- BOTÓN ----------

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    textoBoton: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 14.sp,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    ) {
        Text(textoBoton, color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Bold, fontSize = fontSize)
    }
}

@Composable
@Preview
fun AppButtonPreview(){
    PacetrideTheme(darkTheme = true) {
        AppButton(
            textoBoton = "Ver carrera",
            onClick = {}
        )
    }
}

@Composable
@Preview
fun AppButtonPreview2(){
    PacetrideTheme(darkTheme = true) {
        AppButton(
            textoBoton = "Ver detalles",
            onClick = {}
        )
    }
}

@Composable
@Preview
fun AppButtonPreview3(){
    PacetrideTheme(darkTheme = true) {
        AppButton(
            textoBoton = "Editar perfil",
            onClick = {}
        )
    }
}