package com.example.pacetride.ui.screens.conexiones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.ProfileAsyncImage

@Composable
fun UsuarioCard(
    usuario: Usuario,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(12.dp))
            .clickable { onClick(usuario.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileAsyncImage(
            imageURL = usuario.fotoPerfil,
            size = 48,
            imgSize = 22,
            background = MaterialTheme.colorScheme.background,
            errorIcon = R.drawable.ic_user_profile
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text (
                text = usuario.nombre,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text (
                text = if (usuario.usuario.startsWith("@")) usuario.usuario else "@${usuario.usuario}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
fun UsuarioCardPreview() {
    val usuario = LocalUsuarioProvider.usuarios[0]
    PacetrideTheme (darkTheme = true){
        UsuarioCard(
            usuario = usuario,
            onClick = {}
        )
    }
}