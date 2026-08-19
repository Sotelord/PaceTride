package com.example.pacetride.ui.screens.publicprofile.components.seccion


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box

@Composable
fun SeccionTitulo(
    titulo: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = titulo,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 8.dp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun SeccionTituloPreview() {
    // Lo envuelvo en un Box con padding para que se aprecie mejor en el preview
    Box(modifier = Modifier.padding(16.dp)) {
        SeccionTitulo(
            titulo = "Estadísticas"
        )
    }
}