package com.example.pacetride.ui.utils

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.R

// ---------- BOTÓN ----------

@Composable
fun AppButton(
    textoBoton: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.electric_lime)
        ),
        modifier = modifier
    ) {
        Text(textoBoton, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview
fun AppButtonPreview(){
    AppButton("Ver carrera")
    AppButton("Ver detalles")
}

@Composable
@Preview
fun AppButtonPreview2(){
    AppButton("Ver detalles")
}

@Composable
@Preview
fun AppButtonPreview3(){
    AppButton("Editar perfil")
}