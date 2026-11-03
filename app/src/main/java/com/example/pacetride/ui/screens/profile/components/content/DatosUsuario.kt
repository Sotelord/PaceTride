package com.example.pacetride.ui.screens.profile.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.seguimiento.ContadoresSeguimiento

@Composable
fun DatosUsuario(
    modifier: Modifier = Modifier,
    nombre: String,
    usuario: String,
    email: String? = null,
    ubicacion: String,
    bio: String?,
    onSeguidoresPressed: () -> Unit,
    onSeguidosPressed: () -> Unit,
    followerCount: Int,
    followingCount: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            nombre,
            modifier = Modifier.testTag("perfilNombre"),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "@$usuario",
            modifier = Modifier.testTag("perfilUsuario"),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (email != null) {
            Text(
                email,
                modifier = Modifier.testTag("perfilEmail"),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        UbicacionUsuario(ubicacion)
        Spacer(modifier = Modifier.height(8.dp))
        if (bio != null) {
            Text(
                bio,
                modifier = Modifier.testTag("perfilBio"),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        ContadoresSeguimiento(
            followersCount = followerCount,
            followingCount = followingCount,
            onSeguidoresPressed = onSeguidoresPressed,
            onSeguidosPressed = onSeguidosPressed
        )
    }
}

@Composable
@Preview
fun DatosUsuarioPreview() {
    PacetrideTheme(darkTheme = true) {
        DatosUsuario(
            nombre = "Santiago Rayo",
            usuario = "santiagorayo",
            email = "example.com",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Siempre buscando mi próximo reto 🏃",
            followerCount = 0,
            followingCount = 0,
            onSeguidoresPressed = {},
            onSeguidosPressed = {}
        )
    }
}

@Composable
@Preview
fun DatosUsuarioPreview2() {
    PacetrideTheme(darkTheme = true) {
        DatosUsuario(
            nombre = "Santiago Rayo",
            usuario = "santiagorayo",
            email = "example.com",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Siempre buscando mi próximo reto 🏃",
            followerCount = 0,
            followingCount = 0,
            onSeguidoresPressed = {},
            onSeguidosPressed = {}
        )
    }
}