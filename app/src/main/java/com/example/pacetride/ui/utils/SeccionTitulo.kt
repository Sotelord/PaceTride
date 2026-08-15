package com.example.pacetride.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- TÍTULO DE SECCIÓN ----------

@Composable
fun SeccionTitulo(texto: String, modifier: Modifier = Modifier) {
    Text(
        texto,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    )
}

@Composable
@Preview
fun SeccionTituloPreview(modifier: Modifier = Modifier){
    SeccionTitulo("Próximas carreras")
}

@Composable
@Preview
fun SeccionTituloPreview2(modifier: Modifier = Modifier){
    SeccionTitulo("Elige tu distancia")
}