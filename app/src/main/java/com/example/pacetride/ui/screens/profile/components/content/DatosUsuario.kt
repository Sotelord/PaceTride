package com.example.pacetride.ui.screens.profile.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun DatosUsuario(
    modifier: Modifier = Modifier,
    nombre: String,
    usuario: String,
    email: String? = null,
    ubicacion: String,
    bio: String?,
    followerCount: Int,
    followingCount: Int? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(nombre, color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "@$usuario", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        if (email != null) {
            Text(email, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
        UbicacionUsuario(ubicacion)
        Spacer(modifier = Modifier.height(8.dp))
        if(bio != null){
            Text(bio, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (followingCount != null) {
                ContadorItem(cantidad = followingCount, etiqueta = "Siguiendo")
                Spacer(modifier = Modifier.width(24.dp))
            }
            ContadorItem(cantidad = followerCount, etiqueta = "Seguidores")
        }
    }
}

@Composable
@Preview
fun DatosUsuarioPreview(){
    PacetrideTheme(darkTheme = true) {
        DatosUsuario(
            nombre = "Santiago Rayo",
            usuario = "santiagorayo",
            email = "example.com",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Siempre buscando mi próximo reto 🏃",
            followerCount = 0,
            followingCount = 0
        )
    }
}

@Composable
@Preview
fun DatosUsuarioPreview2(){
    PacetrideTheme(darkTheme = true) {
        DatosUsuario(
            nombre = "Santiago Rayo",
            usuario = "santiagorayo",
            email = "example.com",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Siempre buscando mi próximo reto 🏃",
            followerCount = 0,
        )
    }
}