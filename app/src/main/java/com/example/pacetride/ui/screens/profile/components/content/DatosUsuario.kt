package com.example.pacetride.ui.screens.profile.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DatosUsuario(
    nombre: String,
    usuario: String,
    ubicacion: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(nombre, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(usuario, color = Color.LightGray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        UbicacionUsuario(ubicacion)
        Spacer(modifier = Modifier.height(8.dp))
        Text(bio, color = Color.LightGray, fontSize = 12.sp)
    }
}

@Composable
@Preview
fun DatosUsuarioPreview(modifier: Modifier = Modifier){
    DatosUsuario(
        "Santiago Rayo",
        "@santiagorayo",
        "Bogotá, Colombia",
        "Runner • Siempre buscando mi próximo reto 🏃"
    )
}