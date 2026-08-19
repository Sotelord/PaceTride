package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun AvatarUsuario(idImagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(idImagen),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
    )
}

@Composable
@Preview
fun AvatarUsuarioPreview(modifier: Modifier = Modifier){
    AvatarUsuario(R.drawable.foto_perfil)
}