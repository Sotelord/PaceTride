package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun HeaderPost(
    idAvatar: Int,
    nombre: String,
    tiempo: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarUsuario(idAvatar)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(nombre, color = colorResource(R.color.ice), fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(tiempo, color = colorResource(R.color.mist), fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun HeaderPostPreview(modifier: Modifier = Modifier){
    HeaderPost(R.drawable.foto_perfil, "Santiago Rayo", "Hace 2 h")
}

@Composable
@Preview
fun HeaderPostPreview2(modifier: Modifier = Modifier){
    HeaderPost(R.drawable.foto_perfil, "Laura Gómez", "Hace 5 h")
}

@Composable
@Preview
fun HeaderPostPreview3(modifier: Modifier = Modifier){
    HeaderPost(R.drawable.foto_perfil, "Luis Martínez", "Hace 8 h")
}